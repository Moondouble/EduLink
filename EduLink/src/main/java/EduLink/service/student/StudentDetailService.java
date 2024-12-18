package EduLink.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.StudentDTO;
import EduLink.mapper.StudentMapper;

@Service
public class StudentDetailService {
	@Autowired
	StudentMapper studentMapper;
	public void execute(String studentNum, Model model) {
		StudentDTO dto = studentMapper.studentSelectOne(studentNum);
		model.addAttribute("studentCommand", dto);
		
	}
	
}