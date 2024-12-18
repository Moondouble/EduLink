package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import EduLink.command.StudentCommand;
import EduLink.service.AutoNumService;
import EduLink.service.student.StudentWriteService;

@Controller
@RequestMapping("student")
public class StudentController {
	@Autowired
	StudentWriteService studentWriteService;
	@Autowired
	AutoNumService autoNumService;
	@GetMapping("studentList")
	public String studentList() {
		return "thymeleaf/student/studentList";
	}
	@GetMapping("studentWrite")
	public String studentWrite(Model model) {
		/*
		String autoNum = autoNumService.execute("student_", "student_num", 9, "student");
		StudentCommand  studentCommand = new StudentCommand();
		studentCommand.setStudentNum(autoNum);
		model.addAttribute("studentCommand", studentCommand);
		*/
		return "thymeleaf/student/studentForm";
	}
	@PostMapping("studentRegist")
	public String studentRegist(StudentCommand studentCommand) {
		studentWriteService.execute(studentCommand);
		return "redirect:/";
	}
	
}
