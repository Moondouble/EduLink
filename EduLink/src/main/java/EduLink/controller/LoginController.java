package EduLink.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import EduLink.command.LoginCommand;
import EduLink.service.IdcheckService;
import EduLink.service.login.UserLoginService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	IdcheckService idcheckService;
	@Autowired
	UserLoginService userLoginService;
    @PostMapping("userIdCheck")
    @ResponseBody
    public String userIdCheck(@RequestParam("userId") String userId) {
        String response = new String();
        if (idcheckService.isIdAvailable(userId)) {
            response = "사용가능한 아이디입니다.";
        } else {
            response = "사용중인 아이디입니다.";
        }
        return response;
    }
	 // 로그인 페이지 이동
    @GetMapping("loginPage")
    public String loginPage(@ModelAttribute("loginCommand") LoginCommand loginCommand) {
        return "thymeleaf/login"; // login.html 페이지 반환
    }
    @PostMapping("login")
	// 아이디와 비밀번호를 command로 받아온다.
	public String login(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session) {
		userLoginService.execute(loginCommand, result, session);
		if(result.hasErrors()) {
			return "thymeleaf/login";
		}
		return "redirect:/";
	}   
    @GetMapping("logout")
    public String logout(HttpSession session) {
    	session.invalidate(); // 로그아웃시 모든 session 삭제
    	return "redirect:/"; // 그리고 첫 페이지로 이동
    }
    @GetMapping("registSelect")
    public String registSelect() {
    	return "thymeleaf/login/registSelect";
    }
}