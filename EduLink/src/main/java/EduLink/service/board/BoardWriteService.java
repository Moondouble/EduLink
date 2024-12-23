package EduLink.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.BoardCommand;
import EduLink.domain.BoardDTO;
import EduLink.mapper.BoardMapper;

@Service
public class BoardWriteService {
	@Autowired
	BoardMapper boardMapper;
	
	public void execute(BoardCommand boardCommand) {
		BoardDTO dto = new BoardDTO();
		dto.setBoardNum(boardCommand.getBoardNum());
		dto.setBoardContents(boardCommand.getBoardContents());
		dto.setBoardDate(boardCommand.getBoardDate());
		dto.setBoardName(boardCommand.getBoardName());
		dto.setClassNum(boardCommand.getClassNum());
		dto.setStudentNum(boardCommand.getStudentNum());
		dto.setTeacherNum(boardCommand.getTeacherNum());
		dto.setWriteNum(boardCommand.getWriteNum());
		boardMapper.boardInsert(dto);
	}
}
