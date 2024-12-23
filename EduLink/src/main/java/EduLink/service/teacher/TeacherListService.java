package EduLink.service.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.TeacherDTO;
import EduLink.mapper.TeacherMapper;

@Service
public class TeacherListService {
	@Autowired
	TeacherMapper teacherMapper;
	public void execute(Model model) {
		List<TeacherDTO> list = teacherMapper.teacherSelectAll();
		model.addAttribute("list", list);
		
	}

}
