package EduLink.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.BoardDTO;
import EduLink.mapper.BoardMapper;

@Service
public class BoardListService {
	@Autowired
	BoardMapper boardMapper;
	public void execute(Model model) {
		List<BoardDTO> list = boardMapper.boardSelectAll();
		model.addAttribute("list", list);
		
	}
}
