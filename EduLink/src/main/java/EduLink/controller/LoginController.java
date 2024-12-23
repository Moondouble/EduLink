package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
   // spring 방식
   @PostMapping("userIdCheck")
   public @ResponseBody Integer userIdCheck(String userId) {
      // html, jsp파일경로(x)
      return idcheckService.execute(userId);
      
   }
   @GetMapping("loginPage")
   public String loginPage(Model model) {
       model.addAttribute("loginCommand", new LoginCommand());
      return "thymeleaf/login/loginPage";
   }
   @PostMapping("login")
   public String login(@Validated LoginCommand loginCommand
         ,BindingResult result
         ,HttpSession session) {
      userLoginService.execute(loginCommand, session, result);
      if(result.hasErrors()) {
         return "thymeleaf/login/loginPage";
      }
      System.out.println("User ID: " + loginCommand.getUserId());
        System.out.println("Password: " + loginCommand.getUserPw());
      return "redirect:/";
   }
   @GetMapping("logout")
   public String logout(HttpSession session) {
      session.invalidate();
      return "redirect:/";
   }
}






