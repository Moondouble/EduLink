
package EduLink.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.command.PurchaseCommand;
import EduLink.domain.AuthInfoDTO;
import EduLink.domain.CartDTO;
import EduLink.domain.PurchaseDTO;
import EduLink.domain.PurchaseListDTO;
import EduLink.domain.StudentDTO;
import EduLink.mapper.CartMapper;
import EduLink.mapper.MemberMyMapper;
import EduLink.mapper.PurchaseMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class ClassOrderService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	PurchaseMapper purchaseMapper;
	@Autowired
	CartMapper cartMapper;
	public String execute(PurchaseCommand purchaseCommand, HttpSession session, Model model) {
		String purchaseNum = "";
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		StudentDTO stuDto = memberMyMapper.studentInfo(auth.getUserNum());
		// 구매 번호를 먼저 구해 오겠습니다.
		purchaseNum = purchaseMapper.selectNum();
		//  command 에 있는 상품번호를 split()해서 배열로 받아온다.
		String [] classNums = purchaseCommand.getClassNums().split("-");
		// 각각의 정보들을 구매dto에 저장한다.
		PurchaseDTO dto = new PurchaseDTO();
		dto.setPurchaseNum(purchaseNum);
		dto.setStudentNum(stuDto.getStudentNum());
		dto.setMessage(purchaseCommand.getMessage());
		dto.setPurchasePrice(purchaseCommand.getSumPrice());
		dto.setPurchaseStatus("입금대기중");
		// 구매정보를 데이블에 저장합니다.
		purchaseMapper.purchaseInsert(dto);
		// 이제 구매한 상품정보들을 입력할 차례입니다.
		PurchaseListDTO plDto = new PurchaseListDTO();
		plDto.setClassNums(classNums);
		plDto.setPurchaseNum(purchaseNum);	
		plDto.setStudentNum(stuDto.getStudentNum());
		purchaseMapper.purchaseListInsert(plDto);
		// cart에 있는 정보를 삭제해야 합니다.
		CartDTO cartDto = new CartDTO();
		cartDto.setStudentNum(stuDto.getStudentNum());
		cartDto.setClassNums(classNums);
		cartMapper.cartGoodsDeletes(cartDto);
		return purchaseNum;
	}
}


