package EduLink.service.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.StudentDTO;
import EduLink.domain.StartEndPageDTO;
import EduLink.mapper.StudentMapper;
import EduLink.service.StartEndPageService;

@Service
public class StudentListService {
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	StartEndPageService startEndPageService;
	
	String searchWord;
	
	public void execute(String searchWord, int page, Model model) {
		if(searchWord != null) {
			this.searchWord = searchWord.trim();
		}
		StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
		List<StudentDTO> list = studentMapper.studentSelectAll(sepDTO);
		
		int count = studentMapper.studentCount(this.searchWord);
		startEndPageService.execute(page, count, model, list, this.searchWord);
	}
}