package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import EduLink.service.mypage.MyPageService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("myPage")
public class MyPageController
{
	@Autowired
	MyPageService myPageService;
	@GetMapping("profile")
	public String profile(HttpSession session,Model model) {
		myPageService.execute(model,session);
		return "thymeleaf/myPage/profile";
	}

}
