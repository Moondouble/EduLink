package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	public void boardInsert(BoardDTO dto);
	public List<BoardDTO> boardSelectAll(String classNum);
	public BoardDTO boardSelectOne(String boardNum);
	public void boardUpdate(BoardDTO dto);
	public void boardDelete(String boardNum);
	public List<BoardDTO> boardSelectQuestion(String classNum);
	
}
