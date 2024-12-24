package EduLink.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.BoardDTO;
import EduLink.mapper.BoardMapper;
import EduLink.mapper.StudentMapper;

@Service
public class BoardDetailService {
	@Autowired
	BoardMapper boardMapper;
	public void execute(String writeNum, Model model) {
		BoardDTO dto = boardMapper.boardSelectOne(writeNum);
		model.addAttribute("boardCommand", dto);
	}
}