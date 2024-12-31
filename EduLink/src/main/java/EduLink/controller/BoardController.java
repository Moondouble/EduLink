package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.BoardCommand;
import EduLink.domain.AuthInfoDTO;
import EduLink.domain.ClassroomDTO;
import EduLink.service.AutoNumService;
import EduLink.service.board.BoardDeleteService;
import EduLink.service.board.BoardDetailService;
import EduLink.service.board.BoardListService;
import EduLink.service.board.BoardUpdateService;
import EduLink.service.board.BoardWriteService;
import EduLink.service.classroom.ClassroomDetailService;
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
	public String boardWrite2(BoardCommand boardCommand) {
		boardWriteService.execute(boardCommand);
		return "redirect:boardList?classNum=" + boardCommand.getClassNum();
	}
	@RequestMapping("boardDetail")
	public String boardDetail(@RequestParam("boardNum") String boardNum, Model model) {
		boardDetailService.execute(boardNum, model);
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
	public String boardDelete(@RequestParam("boardNum") String boardNum) {
		boardDeleteService.execute(boardNum);
		return "redirect:/";
	}
	
	
	
}
