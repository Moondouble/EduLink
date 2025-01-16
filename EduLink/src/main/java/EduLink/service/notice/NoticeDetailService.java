package EduLink.service.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.NoticeDTO;
import EduLink.mapper.NoticeMapper;

@Service
public class NoticeDetailService {
	@Autowired
	NoticeMapper noticeMapper;
	
	public void execute(String noticeNum, Model model) {
		NoticeDTO dto = noticeMapper.noticeSelectOne(noticeNum);
		model.addAttribute("noticeCommand", dto);
		System.out.println("noticeNum : " + dto.getNoticeNum());
	}
}
