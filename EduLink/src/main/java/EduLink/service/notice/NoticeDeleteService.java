package EduLink.service.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.EmployeeMapper;
import EduLink.mapper.NoticeMapper;

@Service
public class NoticeDeleteService {
	@Autowired
	NoticeMapper noticeMapper;
	public void execute(String noticeNum) {
		noticeMapper.noticeDelete(noticeNum);
		
	}
}
