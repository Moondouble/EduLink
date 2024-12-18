package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.StudentCommand;
import EduLink.service.AutoNumService;
import EduLink.service.student.StudentDetailService;
import EduLink.service.student.StudentListService;
import EduLink.service.student.StudentUpdateService;
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
	@Autowired
	StudentUpdateService studentUpdateService;
	@GetMapping("studentList")
	public String studentList() {
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
	@GetMapping("studentDetail")
	public String studentDetail(@RequestParam("studentNum") String studentNum, Model model) {
		studentDetailService.execute(studentNum, model);
		return "thymeleaf/student/studentInfo";
	}
	@GetMapping("studentUpdate")
	public String studentUpdate(@RequestParam("studentNum") String studentNum, Model model) {
		studentDetailService.execute(studentNum, model);
		return "thymeleaf/student/studentModify";
	}
	@PostMapping("studentModify")
	public String studentModify(StudentCommand studentCommand) {
		studentUpdateService.execute(studentCommand);
		return "redirect:studentDetail?studentNum="+studentCommand.getStudentNum();
	}
	
	
}
