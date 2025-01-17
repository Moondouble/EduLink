package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import EduLink.command.StudentCommand;
import EduLink.service.AutoNumService;
import EduLink.service.IdcheckService;
import EduLink.service.student.StudentDeleteService;
import EduLink.service.student.StudentDetailService;
import EduLink.service.student.StudentListService;
import EduLink.service.student.StudentUpdateService;
import EduLink.service.student.StudentWriteService;
import EduLink.service.student.StudentsDeleteService;

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
   @Autowired
   StudentDeleteService studentDeleteService;
   @Autowired
   IdcheckService idcheckService;
   @Autowired
   StudentsDeleteService studentsDeleteService;
   
   
   @PostMapping("studentsDelete")
   public String studentsDelete(
   		@RequestParam(value="studentDels") String studentDels []) {
	studentsDeleteService.execute(studentDels);
   	return "redirect:studentList";
   }
   
   @GetMapping("studentList")
   public String studentList(
		   @RequestParam(value="page", required = false, defaultValue = "1") int page,
   		   @RequestParam(value="searchWord" , required=false) String searchWord,
   		   Model model) {
      studentListService.execute(searchWord, page, model);
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
   public String studentRegist(@Validated StudentCommand studentCommand
         ,@RequestParam("studentImage") MultipartFile studentImageFile,
         BindingResult result) {
      if(result.hasErrors()) {
         return "thymeleaf/student/studentForm";
      }
      if(!studentCommand.isStudentPwEqualStudentPwCon()) {
         result.rejectValue("studentPwCon", "studentCommand.studentPwCon"
               , "비밀번호가 일치하지 않습니다.");
         return "thymeleaf/student/studentForm";
      }
      studentWriteService.execute(studentCommand);
      return "redirect:/";
   }
   @RequestMapping("studentDetail")
   public String studentDetail(@RequestParam("studentNum") String studentNum, Model model) {
      studentDetailService.execute(studentNum, model);
      return "thymeleaf/student/studentInfo";
   }
   @RequestMapping("studentUpdate")
   public String studentUpdate(@RequestParam("studentNum") String studentNum, Model model) {
      studentDetailService.execute(studentNum, model);
      return "thymeleaf/student/studentModify";
   }
   @PostMapping("studentModify")
   public String studentModify(StudentCommand studentCommand) {
      studentUpdateService.execute(studentCommand);
      return "redirect:studentDetail?studentNum="+studentCommand.getStudentNum();
   }
   @GetMapping("studentDelete")
   public String studentDelete(@RequestParam("studentNum") String studentNum) {
      studentDeleteService.execute(studentNum);
      return "redirect:/";
   }
   
}
