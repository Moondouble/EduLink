package EduLink.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import EduLink.command.LoginCommand;
import EduLink.domain.AuthInfoDTO;
import EduLink.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class UserLoginService {
	@Autowired
	UserMapper userMapper;
	public void execute(LoginCommand loginCommand, BindingResult result, HttpSession session) {
        String userId = loginCommand.getUserId();
        String userPw = loginCommand.getUserPw();

        // 회원 로그인 정보를 가지고 오기 위한 DTO
        AuthInfoDTO dto = userMapper.loginSelect(userId);
        if (userId != null && !userId.isEmpty()) {
            if (dto != null) { // 회원 아이디가 존재하는 경우
                // 아이디가 존재하고 비밀번호가 일치하는 경우
                if (userPw.equals(dto.getUserPw())) { // 단순 비밀번호 비교
                    System.out.println("비밀번호가 일치");
                    // 아이디와 비밀번호가 일치하면 session에 로그인 정보 저장
                    session.setAttribute("auth", dto);
                } else {
                    System.out.println("비밀번호가 일치하지 않을 때");
                    result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 틀렸습니다.");
                }
            } else {
                System.out.println("아이디가 존재하지 않았을 때");
                result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");
            }
        }
    }

}