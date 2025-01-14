package EduLink.service.reply;

import org.springframework.stereotype.Service;

import EduLink.mapper.ReplyMapper;

@Service
public class ReplyDeleteService {
	ReplyMapper replyMapper;
	public void execute(String replyNum) {
		replyMapper.replyDelete(replyNum);
	}
}
