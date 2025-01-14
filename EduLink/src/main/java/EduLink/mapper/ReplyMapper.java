package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.ReplyDTO;

@Mapper
public interface ReplyMapper
{
	public void replyInsert(ReplyDTO dto);
	public List<ReplyDTO> replySelectboard(String boardNum);
	public void replyUpdate(ReplyDTO dto);
	public void replyDelete(String replyNum);
}
