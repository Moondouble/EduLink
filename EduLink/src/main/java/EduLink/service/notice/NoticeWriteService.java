package EduLink.service.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.NoticeCommand;
import EduLink.domain.NoticeDTO;
import EduLink.mapper.NoticeMapper;

@Service
public class NoticeWriteService {
	@Autowired
	NoticeMapper noticeMapper;
	
	public void execute(NoticeCommand noticeCommand) {
		NoticeDTO dto = new NoticeDTO();
		dto.setEmpNum(noticeCommand.getEmpNum());
		dto.setNoticeContents(noticeCommand.getNoticeContents());
		dto.setNoticeDate(noticeCommand.getNoticeDate());
		dto.setNoticeName(noticeCommand.getNoticeName());
		dto.setNoticeNum(noticeCommand.getNoticeNum());
		noticeMapper.noticeInsert(dto);
	}
}