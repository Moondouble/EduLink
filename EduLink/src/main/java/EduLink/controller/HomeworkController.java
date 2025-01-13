package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.HomeworkCommand;
import EduLink.service.AutoNumService;
import EduLink.service.classroom.ClassroomDetailService;
import EduLink.service.homework.HomeworkDeleteService;
import EduLink.service.homework.HomeworkDetailService;
import EduLink.service.homework.HomeworkListService;
import EduLink.service.homework.HomeworkUpdateService;
import EduLink.service.homework.HomeworkWriteService;

@Controller
@RequestMapping("homework")
public class HomeworkController {
	@Autowired
	ClassroomDetailService classroomDetailService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	HomeworkWriteService homeworkWriteService;
	@Autowired
	HomeworkListService homeworkListService;
	@Autowired
	HomeworkDetailService homeworkDetailService;
	@Autowired
	HomeworkUpdateService homeworkUpdateService;
	@Autowired
	HomeworkDeleteService homeworkDeleteService;
	
	@RequestMapping("homeworkList")
	public String homeworkList(@RequestParam("classNum") String classNum, Model model) {
		homeworkListService.execute(classNum, model);
		classroomDetailService.execute(classNum, model);
		return "thymeleaf/homework/homeworkList";
	}
	
	@GetMapping("homeworkWrite")
	public String homeworkWrite(@RequestParam("classNum") String classNum, Model model) {
		String autoNum = autoNumService.execute("hw_", "hw_num", 4, "homework");
		HomeworkCommand homeworkCommand = new HomeworkCommand();
		homeworkCommand.setHwNum(autoNum);
		model.addAttribute("homeworkCommand", homeworkCommand);
		classroomDetailService.execute(classNum, model);
		return "thymeleaf/homework/homeworkForm";
	}
	
	@PostMapping("homeworkRegist")
	public String homeworkRegist(HomeworkCommand homeworkCommand) {
		homeworkWriteService.execute(homeworkCommand);
		return "redirect:homeworkList?classNum=" + homeworkCommand.getClassNum();
	}
	
	@GetMapping("homeworkDetail")
	public String homeworkDetail(@RequestParam("hwNum") String hwNum, Model model) {
		homeworkDetailService.execute(hwNum, model);
		return "thymeleaf/homework/homeworkInfo";
	}
	
	@RequestMapping("homeworkUpdate")
	public String studentUpdate(@RequestParam("hwNum") String hwNum, Model model) {
		homeworkDetailService.execute(hwNum, model);
		return "thymeleaf/homework/homeworkModify";
	}
	
	@PostMapping("homeworkModify")
	public String homeworkModify(HomeworkCommand homeworkCommand) {
		homeworkUpdateService.execute(homeworkCommand);
		System.out.println("hwNum: " + homeworkCommand.getHwNum());
		return "redirect:homeworkDetail?hwNum="+homeworkCommand.getHwNum();
	}
	
	@GetMapping("homeworkDelete")
	public String homeworkDelete(@RequestParam("hwNum") String hwNum) {
		homeworkDeleteService.execute(hwNum);
		return "redirect:/";
	}
}