package EduLink.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.BoardCommand;
import EduLink.domain.BoardDTO;
import EduLink.mapper.BoardMapper;

@Service
public class BoardUpdateService {
	@Autowired
	BoardMapper boardMapper;
	public void execute(BoardCommand boardCommand) {
		
		BoardDTO dto = new BoardDTO();
		dto.setBoardNum(boardCommand.getBoardNum());
		dto.setBoardName(boardCommand.getBoardName());
		dto.setBoardContents(boardCommand.getBoardContents());
		 
		boardMapper.boardUpdate(dto);
		
	}
}
