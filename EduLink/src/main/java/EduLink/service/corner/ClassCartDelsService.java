package EduLink.service.corner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.domain.AuthInfoDTO;
import EduLink.domain.StudentDTO;
import EduLink.mapper.CartMapper;
import EduLink.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class ClassCartDelsService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	CartMapper cartMapper;
	public String execute(String[] classNums ,  HttpSession session) {
		//회원 정보 가져오기
		AuthInfoDTO auth  = (AuthInfoDTO)session.getAttribute("auth");
		StudentDTO stuDto = memberMyMapper.studentInfo(auth.getUserNum());
		// 이번에는 배열을 그냥 넘기는 것이 아니라 map에 저장해서 넘겨보겠습니다.
		List<String> cs  = new ArrayList<String>();
		// 배열에 있는 값을 리스트에 저장하겠습니다.
		for(String classNum : classNums) {
			cs.add(classNum);
		}
		//리스트에 저장한 값을 리스트에 넣겠습니다.
		Map<String, Object> condition = new HashMap<String, Object>(); 
		condition.put("classNums", cs);
		// map에 회원번호도 넣겠습니다.
		condition.put("studentNum", stuDto.getStudentNum());
		int i = cartMapper.classNumsDelete(condition);
		if (i >= 1) return "200";
		else return "999";
	}
}
