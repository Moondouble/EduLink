package EduLink.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.BoardDTO;
import EduLink.domain.ClassroomDTO;
import EduLink.mapper.BoardMapper;
import EduLink.mapper.ClassroomMapper;

@Service
public class BoardListService {
	@Autowired
	BoardMapper boardMapper;
	
	public void execute(String classNum, Model model) {
		List<BoardDTO> list = boardMapper.boardSelectAll(classNum);
		model.addAttribute("list", list);
	}
	
	public void question(String classNum,Model model) {
		List<BoardDTO> qlist = boardMapper.boardSelectQuestion(classNum);
		model.addAttribute("qlist", qlist);
		
	}
	public void classSelect(String classNum,Model model) {
		List<BoardDTO> clist = boardMapper.boardSelectClass(classNum);
		model.addAttribute("clist", clist);
		for (BoardDTO dto : clist) {
		    System.out.println("BoardNum: " + dto.getBoardNum());
		    System.out.println("TeacherNum: " + dto.getTeacherNum());
		    System.out.println("StudentNum: " + dto.getStudentNum());
		}
	}
}
