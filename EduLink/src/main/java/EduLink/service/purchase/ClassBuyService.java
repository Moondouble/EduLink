package EduLink.service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.AuthInfoDTO;
import EduLink.domain.CartGoodsDTO;
import EduLink.domain.StudentDTO;
import EduLink.mapper.CartMapper;
import EduLink.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class ClassBuyService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	CartMapper cartMapper;
	public void execute(String [] nums, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");	
		StudentDTO stuDto = memberMyMapper.studentInfo(auth.getUserNum());
		//카트로 부터 구매정보를 가지고와야 합니다. // 그런데 문제는 prodCk네 있는 goodsNum만 가지고 와야 합니다.
		List<CartGoodsDTO> list = cartMapper.cartList(stuDto.getStudentNum(), nums);
		Integer sumPrice = 0; // 상품수량 합계금액
		Integer sumTotalPrice = 0; // 결제 금액
		String classNums = ""; // 상품번호들 저장
		for(CartGoodsDTO dto : list) {
			sumTotalPrice += dto.getTotalPrice();
			classNums += dto.getClassroomDTO().getClassNum() + "-";
		}
		sumPrice = sumTotalPrice;
		model.addAttribute("list", list);
		model.addAttribute("sumPrice", sumPrice);
		model.addAttribute("sumTotalPrice", sumTotalPrice);
		model.addAttribute("classNums", classNums);
	}
}
