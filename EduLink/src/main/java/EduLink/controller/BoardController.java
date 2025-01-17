package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import EduLink.command.BoardCommand;
import EduLink.command.ReplyCommand;
import EduLink.domain.AuthInfoDTO;
import EduLink.domain.ReplyDTO;
import EduLink.service.AutoNumService;
import EduLink.service.board.BoardDeleteService;
import EduLink.service.board.BoardDetailService;
import EduLink.service.board.BoardListService;
import EduLink.service.board.BoardUpdateService;
import EduLink.service.board.BoardWriteService;
import EduLink.service.classroom.ClassroomDetailService;
import EduLink.service.reply.ReplyDeleteService;
import EduLink.service.reply.ReplyDetailService;
import EduLink.service.reply.ReplyUpdateService;
import EduLink.service.reply.ReplyWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	BoardWriteService boardWriteService;
	@Autowired
	BoardListService boardListService;
	@Autowired
	BoardDetailService boardDetailService;
	@Autowired
	BoardUpdateService boardUpdateService;
	@Autowired
	BoardDeleteService boardDeleteService;
	@Autowired
	ClassroomDetailService classroomDetailService;
	@Autowired
	ReplyWriteService replyWriteService;
	@Autowired
	ReplyDetailService replyDetailService;
	@Autowired
	ReplyUpdateService replyUpdateService;
	@Autowired
	ReplyDeleteService replyDeleteService;
	@RequestMapping("boardList")
	public String boardList(@RequestParam("classNum") String classNum, Model model) {
		boardListService.execute(classNum, model);
		classroomDetailService.execute(classNum, model);
		return "thymeleaf/board/boardList";
	}
	@GetMapping("boardWrite")
	public String boardWrite(@RequestParam("classNum") String classNum, Model model, HttpSession session) {
		String autoNum = autoNumService.execute("write_", "write_num", 7, "board");
		String autoNum2 = autoNumService.execute("board_", "board_num", 7, "board");
		BoardCommand boardCommand = new BoardCommand();
		boardCommand.setWriteNum(autoNum);
		boardCommand.setBoardNum(autoNum2);
		model.addAttribute("boardCommand", boardCommand);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String userNum = auth.getUserNum();
		boardCommand.setWriter(userNum);
		
		System.out.println("grade:" + auth.getUserNum());
		
		classroomDetailService.execute(classNum, model);
		return "thymeleaf/board/boardForm";
	}
	@PostMapping("boardWrite")
	public String boardWrite2(BoardCommand boardCommand
			, @RequestParam(value = "boardVideo", required = false) MultipartFile boardVideo) {
		  System.out.println("boardCategory: " + boardCommand.getBoardCategory());
		boardWriteService.execute(boardCommand,boardVideo);
		return "redirect:boardList?classNum=" + boardCommand.getClassNum();
	}
	@RequestMapping("boardDetail")
	public String boardDetail(@RequestParam("boardNum") String boardNum, Model model) {
		boardDetailService.execute(boardNum, model);
		replyDetailService.execute(boardNum,model);
		return "thymeleaf/board/boardInfo";
	}
	@RequestMapping("boardUpdate")
	public String studentUpdate(@RequestParam("boardNum") String boardNum, Model model) {
		boardDetailService.execute(boardNum, model);
		return "thymeleaf/board/boardModify";
	}
	@PostMapping("boardModify")
	public String boardModify(BoardCommand boardCommand) {
		boardUpdateService.execute(boardCommand);
		System.out.println("boardNum: " + boardCommand.getBoardNum());
		return "redirect:boardDetail?boardNum="+boardCommand.getBoardNum();
	}
	@GetMapping("boardDelete")	
	public String boardDelete(@RequestParam("classNum")String classNum,@RequestParam("boardNum") String boardNum) {
		boardDeleteService.execute(boardNum);
		return "redirect:boardList?classNum=" + classNum;
	}
    @GetMapping("stockChart")
    public String stockChart() {
    	return "thymeleaf/board/stockChart";
    }
    @PostMapping("replyWrite")
    public String replyWrite(ReplyCommand replyCommand,Model model) {
    	String autoNum = autoNumService.execute("reply_", "reply_num", 7,"reply");
    	replyWriteService.execute(replyCommand,model,autoNum);
    	return "redirect:boardDetail?boardNum="+replyCommand.getBoardNum();
    }
	@PostMapping("replyUpdate")
	public String replyUpdate(@ModelAttribute ReplyCommand replyCommand,Model model) {
		
		String boardNum = replyCommand.getBoardNum();
		replyUpdateService.execute(replyCommand,model);
		 return "redirect:boardDetail?boardNum=" + boardNum;
	}
	@GetMapping("replyDelete")
	public String replyDelete(@RequestParam("replyNum")String replyNum,@RequestParam("boardNum")String boardNum) {
		replyDeleteService.execute(replyNum);
		return "redirect:boardDetail?boardNum="+boardNum;
	}
	
}
