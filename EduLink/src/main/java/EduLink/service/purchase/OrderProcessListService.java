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
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		StudentDTO stuDto = memberMyMapper.studentInfo(auth.getUserNum());
		List<OrderListDTO> list = purchaseMapper.orderList(stuDto.getStudentNum(), null);
		model.addAttribute("list", list);
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
