package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import EduLink.command.StudentCommand;
import EduLink.service.AutoNumService;
import EduLink.service.student.StudentDetailService;
import EduLink.service.student.StudentListService;
import EduLink.service.student.StudentWriteService;

@Controller
@RequestMapping("student")
public class StudentController {
	@Autowired
	StudentWriteService studentWriteService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	StudentListService studentListService;
	@Autowired
	StudentDetailService studentDetailService;
	
	@GetMapping("studentList")
	public String studentList(Model model) {
		studentListService.execute(model);
		return "thymeleaf/student/studentList";
	}
	@GetMapping("studentWrite")
	public String studentWrite(Model model) {
		String autoNum = autoNumService.execute("student_", "student_num", 9, "student");
		StudentCommand  studentCommand = new StudentCommand();
		studentCommand.setStudentNum(autoNum);
		model.addAttribute("studentCommand", studentCommand);
		return "thymeleaf/student/studentForm";
	}
	@PostMapping("studentRegist")
	public String studentRegist(StudentCommand studentCommand) {
		studentWriteService.execute(studentCommand);
		return "redirect:/";
	}
	@GetMapping("studentDetail/{studentNum}")
	public String studentDetail(@PathVariable("studentNum") String studentNum, Model model) {
		studentDetailService.execute(model, studentNum);
		return "thymeleaf/student/studentInfo";
	}
	
	
}
