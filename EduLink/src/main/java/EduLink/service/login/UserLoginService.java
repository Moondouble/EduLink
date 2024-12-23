package EduLink.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import EduLink.command.LoginCommand;
import EduLink.domain.AuthInfoDTO;
import EduLink.mapper.LoginMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class UserLoginService {
    @Autowired
    LoginMapper loginMapper;

    public void execute(LoginCommand loginCommand, HttpSession session, BindingResult result) {
        AuthInfoDTO auth = loginMapper.loginSelectOne(loginCommand.getUserId());

        if (auth != null) {
            System.out.println("아이디가 존재합니다.");
            
            if (loginCommand.getUserPw().equals(auth.getUserPw())) {
                System.out.println("비밀번호가 일치합니다.");
                session.setAttribute("auth", auth);
            } else {
                result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 틀렸습니다.");
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
        } else {
            result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");
            System.out.println("아이디가 존재하지 않습니다.");
        }
    }
}
