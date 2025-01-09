package EduLink.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.command.PurchaseCommand;
import EduLink.domain.AuthInfoDTO;
import EduLink.domain.StudentDTO;
import EduLink.mapper.CartMapper;
import EduLink.mapper.MemberMyMapper;
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
		
		return purchaseNum;
	}
}
