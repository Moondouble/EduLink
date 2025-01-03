package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.BoardCommand;
import EduLink.command.ClassroomCommand;
import EduLink.domain.AuthInfoDTO;
import EduLink.domain.ClassroomDTO;
import EduLink.domain.TeacherDTO;
import EduLink.service.AutoNumService;
import EduLink.service.classroom.ClassroomDeleteService;
import EduLink.service.classroom.ClassroomDetailService;
import EduLink.service.classroom.ClassroomListService;
import EduLink.service.classroom.ClassroomUpdateService;
import EduLink.service.classroom.ClassroomWriteService;
import EduLink.service.teacher.TeacherDetailService;
import jakarta.servlet.http.HttpSession;


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
	@Autowired
	TeacherDetailService teacherDetailService;
	@GetMapping("classList")
	public String List(Model model) {
		classroomListService.execute(model);
		return "thymeleaf/class/classList";
	}
	@GetMapping("classWrite")
	public String Write(Model model, HttpSession session) {
		String autoNum = autoNumService.execute("class_", "class_num", 7, "classroom");
		ClassroomCommand classroomCommand = new ClassroomCommand();
		classroomCommand.setClassNum(autoNum);
		
		BoardCommand boardCommand = new BoardCommand();
		boardCommand.setClassNum(autoNum);
		
		System.out.println(classroomCommand.getClassNum());

		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String userNum = auth.getUserNum();
		classroomCommand.setTeacherNum(userNum);
		
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
		ClassroomDTO classroomCommand = (ClassroomDTO) model.getAttribute("classroomCommand");
		String teacherNum = classroomCommand.getTeacherNum();
		System.out.println(teacherNum);
		teacherDetailService.execute(teacherNum, model);
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
