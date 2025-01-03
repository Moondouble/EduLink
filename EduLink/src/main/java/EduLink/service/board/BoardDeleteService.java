package EduLink.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.BoardMapper;
import EduLink.mapper.StudentMapper;

@Service
public class BoardDeleteService {
	@Autowired
	BoardMapper boardMapper;
	public void execute(String boardNum) {
		
		boardMapper.boardDelete(boardNum);
		
	}
}
