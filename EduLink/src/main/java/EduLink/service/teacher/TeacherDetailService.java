package EduLink.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.StudentDTO;
import EduLink.domain.TeacherDTO;
import EduLink.mapper.StudentMapper;
import EduLink.mapper.TeacherMapper;

@Service
public class TeacherDetailService {
	@Autowired
	TeacherMapper teacherMapper;
	public void execute(String teacherNum, Model model) {
		TeacherDTO dto = teacherMapper.teacherSelectOne(teacherNum);
		model.addAttribute("teacherCommand", dto);
		
		
	}

}
