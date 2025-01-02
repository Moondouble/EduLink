package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.InquireCommand;
import EduLink.domain.AuthInfoDTO;
import EduLink.service.AutoNumService;
import EduLink.service.inquire.InquireDeleteService;
import EduLink.service.inquire.InquireDetailService;
import EduLink.service.inquire.InquireListService;
import EduLink.service.inquire.InquireUpdateService;
import EduLink.service.inquire.InquireWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("inquire")
public class InquireController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	InquireWriteService inquireWriteService;
	@Autowired
	InquireListService inquireListService;
	@Autowired
	InquireDetailService inquireDetailService;
	@Autowired
	InquireUpdateService inquireUpdateService;
	@Autowired
	InquireDeleteService inquireDeleteService;
	
	@GetMapping("inquireList")
	public String inquireList(Model model) {
		inquireListService.execute(model);
		return "thymeleaf/inquire/inquireList";
	}
	@GetMapping("inquireWrite")
	public String inquireWrite(Model model, HttpSession session) {
		String autoNum = autoNumService.execute("inquire_", "inquire_num", 9, "inquire");
		InquireCommand inquireCommand = new InquireCommand();
		inquireCommand.setInquireNum(autoNum);
		model.addAttribute("inquireCommand", inquireCommand);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String userNum = auth.getUserNum();
		inquireCommand.setWriter(userNum);
		
		
		return "thymeleaf/inquire/inquireForm";
	}
	@PostMapping("inquireRegist")
	public String inquireRegist(InquireCommand inquireCommand) {
		inquireWriteService.execute(inquireCommand);
		return "redirect:inquireList";
	}
	@RequestMapping("inquireDetail")
	public String inquireDetail(@RequestParam("inquireNum") String inquireNum, Model model) {
		inquireDetailService.execute(inquireNum, model);
		return "thymeleaf/inquire/inquireInfo";
	}
	@RequestMapping("inquireUpdate")
	public String inquireUpdate(@RequestParam("inquireNum") String inquireNum, Model model) {
		inquireDetailService.execute(inquireNum, model);
		return "thymeleaf/inquire/inquireModify";
	}
	@PostMapping("inquireModify")
	public String inquireModify(InquireCommand inquireCommand) {
		inquireUpdateService.execute(inquireCommand);
		System.out.println("inquireNum: " + inquireCommand.getInquireNum());
		return "redirect:inquireDetail?inquireNum="+inquireCommand.getInquireNum();
	}
	@GetMapping("inquireDelete")
	public String boardDelete(@RequestParam("inquireNum") String inquireNum, InquireCommand inquireCommand) {
		inquireDeleteService.execute(inquireNum);
		return "redirect:inquireList";
	}
	
	
}
