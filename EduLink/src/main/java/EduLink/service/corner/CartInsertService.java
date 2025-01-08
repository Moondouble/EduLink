package EduLink.service.corner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.domain.AuthInfoDTO;
import EduLink.domain.CartDTO;
import EduLink.domain.StudentDTO;
import EduLink.mapper.CartMapper;
import EduLink.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class CartInsertService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	CartMapper cartMapper;
	public String execute(String classNum, Integer qty, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		// 로그인 정보가 있는지 확인 합니다. 로그인 오류가 발생할 수 있습니다.
		if(auth != null) {
			// 직원은 장바구니를 사용할 수 없으므로 오류가 발생할 수 있습니다.
			if(auth.getGrade().equals("student")) {
				// 회원번호를 가져오겠습니다.
				StudentDTO stuDto = memberMyMapper.studentInfo(auth.getUserNum());
				//장바구니 dto에 정보를 저장합니다.
				CartDTO dto = new CartDTO();
				dto.setCartQty(qty);
				dto.setClassNum(classNum);
				dto.setStudentNum(stuDto.getStudentNum());
				cartMapper.cartInsert(dto);
				return "200"; //정상처리됨
			}else {
				System.out.println("학생로그인을 하세요");
				return "999";
			}
		}else {
			System.out.println("로그인을 해야합니다.");
			return "000";
		}
	}
}
