package EduLink.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.NoticeDTO;
import EduLink.mapper.NoticeMapper;

@Service
public class NoticeListService {
	@Autowired
	NoticeMapper noticeMapper;
	public void execute(Model model) {
		List<NoticeDTO> list1 = noticeMapper.noticeSelectAll();
		model.addAttribute("list1", list1);
		
	}
}
