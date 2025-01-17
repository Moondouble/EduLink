package EduLink.service.purchase;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
import com.inicis.std.util.SignatureUtil;

import EduLink.domain.AuthInfoDTO;
import EduLink.domain.PaymentDTO;
import EduLink.domain.PurchaseDTO;
import EduLink.domain.StudentDTO;
import EduLink.mapper.PurchaseMapper;
import EduLink.mapper.StudentMapper;
import EduLink.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class IniPayReturnService {
   @Autowired
   PurchaseMapper purchaseMapper;
   @Autowired
   StudentMapper studentMapper;
   @Autowired
   UserMapper userMapper;
   public void execute(HttpServletRequest request,HttpSession session, Model model) {
      //여기에 있는내용도 이니시스로 받은 코드입니다.
      Map<String, String> resultMap = new HashMap<String, String>();
      
      try{
         //#############################
         // 인증결과 파라미터 일괄 수신
         //#############################
         request.setCharacterEncoding("UTF-8");

         Map<String,String> paramMap = new Hashtable<String,String>();

         Enumeration elems = request.getParameterNames();

         String temp = "";

         while(elems.hasMoreElements())
         {
            temp = (String) elems.nextElement();
            paramMap.put(temp, request.getParameter(temp));
         }
         
         System.out.println("paramMap : "+ paramMap.toString());
         
         
         if("0000".equals(paramMap.get("resultCode"))){

            System.out.println("####인증성공/승인요청####");

            //############################################
            // 1.전문 필드 값 설정(***가맹점 개발수정***)
            //############################################
            
            String mid       = paramMap.get("mid");
            String timestamp= SignatureUtil.getTimestamp();
            String charset    = "UTF-8";
            String format    = "JSON";
            String authToken= paramMap.get("authToken");
            String authUrl   = paramMap.get("authUrl");
            String netCancel= paramMap.get("netCancelUrl");   
            String merchantData = paramMap.get("merchantData");
            
            //#####################
            // 2.signature 생성
            //#####################
            Map<String, String> signParam = new HashMap<String, String>();

            signParam.put("authToken",   authToken);      // 필수
            signParam.put("timestamp",   timestamp);      // 필수

            // signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
            String signature = SignatureUtil.makeSignature(signParam);


            //#####################
            // 3.API 요청 전문 생성
            //#####################
            Map<String, String> authMap = new Hashtable<String, String>();

            authMap.put("mid"         ,mid);         // 필수
            authMap.put("authToken"      ,authToken);   // 필수
            authMap.put("signature"      ,signature);   // 필수
            authMap.put("timestamp"      ,timestamp);   // 필수
            authMap.put("charset"      ,charset);      // default=UTF-8
            authMap.put("format"      ,format);       // default=XML


            HttpUtil httpUtil = new HttpUtil();

            try{
               //#####################
               // 4.API 통신 시작
               //#####################

               String authResultString = "";

               authResultString = httpUtil.processHTTP(authMap, authUrl);
               
               //############################################################
               //5.API 통신결과 처리(***가맹점 개발수정***)
               //############################################################
               
               String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ","").replace("\n", "").replace("}", "").replace("{", "");
               
                        
               resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱
               
               
              // 수신결과를 파싱후 resultCode가 "0000"이면 승인성공 이외 실패

              //throw new Exception("강제 Exception");
               ///////여기에 필요한 코드를 추가하겠습니다.
               // 결제된 정보를 테이블에 저장합니다. 필요한 정보들은 로그를 통해 이니시스가 보낸내용을 확인할 수 있습니다.
               PaymentDTO dto = new PaymentDTO();
               dto.setApplDate(resultMap.get("applDate")); //결제 날짜
               dto.setApplTime(resultMap.get("applTime")); // 결제 시간
               dto.setCardNum(resultMap.get("CARD_Num"));
               dto.setConfirmNumber(resultMap.get("applNum"));
               dto.setPayMethod(resultMap.get("payMethod"));
               dto.setPurchaseName(resultMap.get("CARD_PurchaseName"));
               dto.setPurchaseNum(resultMap.get("MOID"));
               dto.setResultMessage(resultMap.get("resultMsg"));
               dto.setTid(resultMap.get("tid"));
               dto.setTotal_Price(resultMap.get("TotPrice"));
               int i = purchaseMapper.paymentInsert(dto);
               // 결제완료로 구매상태를 변경합니다.
               if(i >= 1) {
                  purchaseMapper.purchaseStatusUpdate("결제완료", dto.getPurchaseNum());
               }

               // 이니시스결제 후  session이 만료됩니다.. 추가 
               PurchaseDTO pDto = purchaseMapper.purchaseSelect(resultMap.get("MOID"));
               StudentDTO stuDto = studentMapper.studentSelectOne(pDto.getStudentNum());
               AuthInfoDTO auth = userMapper.loginSelect(stuDto.getStudentId());
               session.setAttribute("auth", auth);
               // html에 전달될 내용 model에 저장
               model.addAttribute("userId", auth.getUserId());
               model.addAttribute("price", dto.getTotal_Price());
               /////
               
            } catch (Exception ex) {

               //####################################
               // 실패시 처리(***가맹점 개발수정***)
               //####################################

               //---- db 저장 실패시 등 예외처리----//
               //System.out.println(ex);

               //#####################
               // 망취소 API
               //#####################
               //String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);   // 망취소 요청 API url(고정, 임의 세팅 금지)

               //out.println("## 망취소 API 결과 ##");

               // 취소 결과 확인
               //out.println("<p>"+netcancelResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;")+"</p>");
            }

         }else{
            
            resultMap.put("resultCode", paramMap.get("resultCode"));
            resultMap.put("resultMsg", paramMap.get("resultMsg"));
         }

      }catch(Exception e){

         System.out.println(e);
      }
   }
}