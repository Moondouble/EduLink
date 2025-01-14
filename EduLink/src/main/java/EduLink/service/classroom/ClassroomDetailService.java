package EduLink.service.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.ClassroomDTO;
import EduLink.mapper.ClassroomMapper;

@Service
public class ClassroomDetailService {
	@Autowired
	ClassroomMapper classroomMapper;

	public void execute(String classNum, Model model) {
		ClassroomDTO dto = classroomMapper.classSelectOne(classNum);
		model.addAttribute("classroomCommand", dto);
	}
}
