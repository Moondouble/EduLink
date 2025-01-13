package EduLink.service.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.command.ReplyCommand;
import EduLink.domain.ReplyDTO;
import EduLink.mapper.ReplyMapper;

@Service
public class ReplyWriteService
{
	@Autowired
	ReplyMapper replyMapper;
	public void execute(ReplyCommand replyCommand,Model model,String autoNum) {
		ReplyDTO dto = new ReplyDTO();
		dto.setReplyNum(autoNum);
		dto.setBoardNum(replyCommand.getBoardNum());
		dto.setTeacherNum(replyCommand.getTeacherNum());
		dto.setReplyContents(replyCommand.getReplyContents());
		replyMapper.replyInsert(dto);
		
	}
}
