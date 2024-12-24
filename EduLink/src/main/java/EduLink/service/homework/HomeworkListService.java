package EduLink.service.homework;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.HomeworkDTO;
import EduLink.mapper.HomeworkMapper;

@Service
public class HomeworkListService {
	@Autowired
	HomeworkMapper homeworkMapper;
	public void execute(String classNum, Model model) {
		List<HomeworkDTO> list = homeworkMapper.homeworkSelectAll(classNum);
		model.addAttribute("list", list);
		
	}
}
