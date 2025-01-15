package EduLink.service.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.ReplyMapper;

@Service
public class ReplyDeleteService {
	@Autowired
	ReplyMapper replyMapper;
	public void execute(String replyNum) {
		replyMapper.replyDelete(replyNum);
	}
}
