package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.NoticeCommand;
import EduLink.domain.AuthInfoDTO;
import EduLink.service.AutoNumService;
import EduLink.service.notice.NoticeDeleteService;
import EduLink.service.notice.NoticeDetailService;
import EduLink.service.notice.NoticeListService;
import EduLink.service.notice.NoticeUpdateService;
import EduLink.service.notice.NoticeWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("notice")
public class NoticeController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	NoticeWriteService noticeWriteService;
	@Autowired
	NoticeListService noticeListService;
	@Autowired
	NoticeDetailService noticeDetailService;
	@Autowired
	NoticeUpdateService noticeUpdateService;
	@Autowired
	NoticeDeleteService noticeDeleteService;
	
	@GetMapping("noticeList")
	public String noticeList(HttpSession session, Model model) {
		noticeListService.execute(model);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		model.addAttribute("auth", auth);
		if (auth != null) { 
			auth.getGrade();
			System.out.println("로그인 되었습니다: " + auth.getGrade());
		}
		
		return "thymeleaf/notice/noticeList";
	}
	
	@GetMapping("noticeWrite")
	public String noticeWrite(HttpSession session, Model model) {
		String autoNum = autoNumService.execute("notice_", "notice_num", 8, "notice");
		NoticeCommand noticeCommand = new NoticeCommand();
		noticeCommand.setNoticeNum(autoNum);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String userNum = auth.getUserNum();
		noticeCommand.setEmpNum(userNum);
		
		model.addAttribute("noticeCommand", noticeCommand);
		return "thymeleaf/notice/noticeForm";
	}
	
	@PostMapping("noticeRegist")
	public String noticeRegist(NoticeCommand noticeCommand) {
		noticeWriteService.execute(noticeCommand);
		return "redirect:noticeList";
	}
	
	@GetMapping("noticeDetail")
	public String noticeDetail(@RequestParam("noticeNum")String noticeNum, HttpSession session, Model model) {
		noticeDetailService.execute(noticeNum, model);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		model.addAttribute("auth", auth);
		if (auth != null) { 
			auth.getGrade();
			System.out.println("로그인 되었습니다: " + auth.getGrade());
		}
		
		return "thymeleaf/notice/noticeInfo";
	}
	
	@GetMapping("noticeUpdate")
	public String noticeUpdate(@RequestParam("noticeNum")String noticeNum, Model model) {
		noticeDetailService.execute(noticeNum, model);
		return "thymeleaf/notice/noticeModify";
	}
	
	@PostMapping("noticeModify")
	public String noticeModify(NoticeCommand noticeCommand) {
		noticeUpdateService.execute(noticeCommand);
		return "redirect:noticeDetail?noticeNum="+noticeCommand.getNoticeNum();
	}
	
	@GetMapping("noticeDelete")
	public String noticeDelete(@RequestParam("noticeNum") String noticeNum) {
		noticeDeleteService.execute(noticeNum);
		return "redirect:/";
	}
	
	
}
