package EduLink.service.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.StudentDTO;
import EduLink.mapper.StudentMapper;

@Service
public class StudentListService {
	@Autowired
	StudentMapper studentMapper;
	public void execute(Model model) {
		List<StudentDTO> list = studentMapper.studentSelectAll();
		model.addAttribute("list", list);
		
	}
	
}
