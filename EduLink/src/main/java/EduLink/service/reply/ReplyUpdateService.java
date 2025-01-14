package EduLink.service.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.command.ReplyCommand;
import EduLink.domain.ReplyDTO;
import EduLink.mapper.ReplyMapper;

@Service
public class ReplyUpdateService {
	@Autowired
	ReplyMapper replyMapper;
	public void execute(ReplyCommand replyCommand,Model model) {
		ReplyDTO dto = new ReplyDTO();
		dto.setReplyContents(replyCommand.getReplyContents());
		replyMapper.replyUpdate(dto);
		
	}
}
