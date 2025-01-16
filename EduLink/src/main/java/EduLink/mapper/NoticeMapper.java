package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.NoticeDTO;

@Mapper
public interface NoticeMapper {
	public void noticeInsert(NoticeDTO dto);
	public List<NoticeDTO> noticeSelectAll();
	public NoticeDTO noticeSelectOne(String noticeNum);
	public void noticeUpdate(NoticeDTO dto);
	public void noticeDelete(String noticeNum);
}
