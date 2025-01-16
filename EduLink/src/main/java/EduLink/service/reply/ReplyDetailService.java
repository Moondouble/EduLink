package EduLink.service.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.ReplyDTO;
import EduLink.mapper.ReplyMapper;

@Service
public class ReplyDetailService
{
	@Autowired
	ReplyMapper replyMapper;
public void execute(String boardNum,Model model) {
	List<ReplyDTO> list = replyMapper.replySelectboard(boardNum);
	model.addAttribute("list", list);
}

}
