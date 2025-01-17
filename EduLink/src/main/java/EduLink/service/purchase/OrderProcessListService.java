package EduLink.service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.AuthInfoDTO;
import EduLink.domain.OrderListDTO;
import EduLink.domain.StudentDTO;
import EduLink.mapper.MemberMyMapper;
import EduLink.mapper.PurchaseMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class OrderProcessListService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	PurchaseMapper purchaseMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
	    StudentDTO stuDto = memberMyMapper.studentInfo(auth.getUserNum());

	    // stuDto가 null인 경우 처리
	    if (stuDto == null) {
	    	if ("teacher".equals(auth.getGrade()) || "employee".equals(auth.getGrade())) {
                // teacher 또는 employee일 경우에는 영상 링크만 모델에 전달
                model.addAttribute("message", "학생 정보가 없지만, 교사 또는 직원 권한으로 영상 링크를 표시합니다.");
                model.addAttribute("isTeacherOrEmployee", true); // 영상 링크 표시 여부
            } else {
                // teacher 또는 employee가 아닌 경우, 오류 메시지
                model.addAttribute("error", "학생 정보가 없으며, 교사 또는 직원이 아닌 사용자입니다.");
            }
        } else {
            // 학생 정보가 있을 경우, 학생의 주문 목록 처리
            List<OrderListDTO> list = purchaseMapper.orderList(stuDto.getStudentNum(), null);
            model.addAttribute("olist", list);
        }

	    model.addAttribute("grade", auth.getGrade());
	}

	    public void execute1(HttpSession session, Model model) {
	        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
	        StudentDTO stuDto = memberMyMapper.studentInfo(auth.getUserNum());
	        List<OrderListDTO> list = purchaseMapper.orderList(stuDto.getStudentNum(), null);
	        
	        // 구매한 강의 목록을 모델에 추가
	        model.addAttribute("purchasedClasses", list);
	        model.addAttribute("userNum", auth.getUserNum());
	    }
	

}
