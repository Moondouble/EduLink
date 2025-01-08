package EduLink.service.corner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.domain.AuthInfoDTO;
import EduLink.domain.StudentDTO;
import EduLink.mapper.CartMapper;
import EduLink.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class ClassCartDelService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	CartMapper cartMapper;
	public void execute(String classNum , HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		StudentDTO dto = memberMyMapper.studentInfo(auth.getUserId());
		cartMapper.classNumDelete(classNum, dto.getStudentNum());
	}
}
