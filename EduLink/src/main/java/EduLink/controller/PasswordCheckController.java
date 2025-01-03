package EduLink.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkRest")
public class PasswordCheckController {

    @PostMapping("/validatePassword")
    public ResponseEntity<String> validatePassword(@RequestParam("password") String password) {
        String passwordPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

        if (password.matches(passwordPattern)) {
            return ResponseEntity.ok("사용 가능한 비밀번호입니다.");
        } else {
            return ResponseEntity.badRequest().body("비밀번호는 영문/숫자/특수문자 조합, 8글자 이상이어야 합니다.");
        }
    }
}