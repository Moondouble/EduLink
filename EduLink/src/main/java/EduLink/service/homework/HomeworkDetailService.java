package EduLink.service.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.HomeworkDTO;
import EduLink.domain.StudentDTO;
import EduLink.mapper.HomeworkMapper;
import EduLink.mapper.StudentMapper;

@Service
public class HomeworkDetailService {
	@Autowired
	HomeworkMapper homeworkMapper;
	public void execute(String hwNum, Model model) {
		HomeworkDTO dto = homeworkMapper.homeworkSelectOne(hwNum);
		model.addAttribute("homeworkCommand", dto);
	}
	
}
