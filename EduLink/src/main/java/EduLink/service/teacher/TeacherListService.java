package EduLink.service.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.StartEndPageDTO;
import EduLink.domain.TeacherDTO;
import EduLink.mapper.TeacherMapper;
import EduLink.service.StartEndPageService;

@Service
public class TeacherListService {
	@Autowired
	TeacherMapper teacherMapper;
	@Autowired
	StartEndPageService startEndPageService;
	
	String searchWord;
	
	public void execute(String searchWord, int page, Model model) {
		if(searchWord != null) {
			this.searchWord = searchWord.trim();
		}
		StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
		List<TeacherDTO> list = teacherMapper.teacherSelectAll(sepDTO);
		
		int count = teacherMapper.teacherCount(this.searchWord);
		startEndPageService.execute(page, count, model, list, this.searchWord);
		
		
		model.addAttribute("teacherList", list);
	}
}

