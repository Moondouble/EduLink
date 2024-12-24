package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.BoardCommand;
import EduLink.service.AutoNumService;
import EduLink.service.board.BoardDeleteService;
import EduLink.service.board.BoardDetailService;
import EduLink.service.board.BoardListService;
import EduLink.service.board.BoardUpdateService;
import EduLink.service.board.BoardWriteService;

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
	
	@GetMapping("boardList")
	public String boardList(Model model) {
		boardListService.execute(model);
		return "thymeleaf/board/boardList";
	}
	@GetMapping("boardWrite")
	public String boardWrite(Model model) {
		String autoNum = autoNumService.execute("write_", "write_num", 7, "board");
		String autoNum2 = autoNumService.execute("board_", "board_num", 7, "board");
		BoardCommand boardCommand = new BoardCommand();
		boardCommand.setWriteNum(autoNum);
		boardCommand.setBoardNum(autoNum2);
		model.addAttribute("boardCommand", boardCommand);
		return "thymeleaf/board/boardForm";
	}
	@PostMapping("boardWrite")
	public String boardWrite2(BoardCommand boardCommand) {
		boardWriteService.execute(boardCommand);
		return "redirect:boardList";
	}
	@RequestMapping("boardDetail")
	public String boardDetail(@RequestParam("writeNum") String writeNum, Model model) {
		boardDetailService.execute(writeNum, model);
		return "thymeleaf/board/boardInfo";
	}
	@RequestMapping("boardUpdate")
	public String studentUpdate(@RequestParam("writeNum") String writeNum, Model model) {
		boardDetailService.execute(writeNum, model);
		return "thymeleaf/board/boardModify";
	}
	@PostMapping("boardModify")
	public String boardModify(BoardCommand boardCommand) {
		boardUpdateService.execute(boardCommand);
		System.out.println("writeNum: " + boardCommand.getWriteNum());
		return "redirect:boardDetail?writeNum="+boardCommand.getWriteNum();
	}
	@GetMapping("boardDelete")
	public String boardDelete(@RequestParam("writeNum") String writeNum) {
		boardDeleteService.execute(writeNum);
		return "redirect:/";
	}
	
	
	
}