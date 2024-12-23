package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.ClassroomCommand;
import EduLink.service.AutoNumService;
import EduLink.service.classroom.ClassroomDeleteService;
import EduLink.service.classroom.ClassroomDetailService;
import EduLink.service.classroom.ClassroomListService;
import EduLink.service.classroom.ClassroomUpdateService;
import EduLink.service.classroom.ClassroomWriteService;


@Controller
@RequestMapping("class")
public class ClassroomController
{
	@Autowired
	ClassroomListService classroomListService;
	@Autowired
	ClassroomWriteService classroomWriteService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	ClassroomDetailService classroomDetailService;
	@Autowired
	ClassroomUpdateService classroomUpdateService;
	@Autowired
	ClassroomDeleteService classroomDeleteService;
	@GetMapping("classList")
	public String List(Model model) {
		classroomListService.execute(model);
		return "thymeleaf/class/classList";
	}
	@GetMapping("classWrite")
	public String Write(Model model) {
		String autoNum = autoNumService.execute("class_", "class_num", 9, "classroom");
		ClassroomCommand classroomCommand = new ClassroomCommand();
		classroomCommand.setClassNum(autoNum);
		System.out.println(classroomCommand.getClassNum());

		model.addAttribute("classroomCommand", classroomCommand);
		System.out.println("Generated classroomCommand: " + classroomCommand);
		return "thymeleaf/class/classForm";
	}
	@PostMapping("classRegist")
	public String Regist(ClassroomCommand classroomCommand) {
		classroomWriteService.execute(classroomCommand);
		return "redirect:classList";
	}
	
	@GetMapping("classDetail")
	public String detail(@RequestParam("classNum") String classNum,Model model) {
		classroomDetailService.execute(classNum,model);
		return "thymeleaf/class/classInfo";
	}
	
	@GetMapping("classUpdate")
	public String Update(@RequestParam("classNum") String classNum,Model model) {
		classroomDetailService.execute(classNum,model);
		return "thymeleaf/class/classModify";	
	}
	@PostMapping("classModify")
	public String Modify(ClassroomCommand classroomCommand) {
		classroomUpdateService.execute(classroomCommand);
		return "redirect:classDetail?classNum="+classroomCommand.getClassNum();
	}
	@GetMapping("classDelete")
	public String Delete(@RequestParam("classNum") String classNum) {
		classroomDeleteService.execute(classNum);
		return "redirect:classList";
	}
}
