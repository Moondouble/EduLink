package EduLink.service.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.mapper.FindMapper;

@Service
public class FindPwService {
	@Autowired
	FindMapper findMapper;
	public void execute(String userId, String userName, String userEmail, Model model) {
		String userPw = findMapper.findPw(userId, userName, userEmail);
        model.addAttribute("userPw", userPw);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        model.addAttribute("userEmail", userEmail);
		
	}

}
