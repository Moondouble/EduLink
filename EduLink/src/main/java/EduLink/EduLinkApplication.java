package EduLink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import EduLink.command.InquireCommand;
import EduLink.domain.AuthInfoDTO;
import EduLink.service.AutoNumService;
import EduLink.service.classroom.ClassroomListService;
import EduLink.service.teacher.TeacherDetailService;
import EduLink.service.teacher.TeacherListService;
import jakarta.servlet.http.HttpSession;

@SpringBootApplication
@Controller
public class EduLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduLinkApplication.class, args);
    }

    @Autowired
    AutoNumService autoNumService;
    @Autowired
    TeacherListService teacherListService;
    @Autowired
    ClassroomListService classroomListService;
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
    	String autoNum = autoNumService.execute("inquire_", "inquire_num", 9, "inquire");
		InquireCommand inquireCommand = new InquireCommand();
		inquireCommand.setInquireNum(autoNum);
		model.addAttribute("inquireCommand", inquireCommand);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		model.addAttribute("auth", auth);
		if (auth != null) { 
			auth.getGrade();
			System.out.println("로그인 되었습니다: " + auth.getGrade());
		}

		classroomListService.execute(model);
		
        return "thymeleaf/index";
    }
}
