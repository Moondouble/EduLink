package EduLink.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.AuthInfoDTO;
import EduLink.domain.UserInfoDTO;
import EduLink.mapper.MyPageMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MyPageService {
    @Autowired
    MyPageMapper myPageMapper;

    public void execute(Model model, HttpSession session) {
        // 세션에서 userId 가져오기
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String userId = auth.getUserId();
        if (userId != null) {
            // userId를 이용해 사용자 정보 조회
            UserInfoDTO dto = myPageMapper.userSelectOne(userId);

            if (dto != null) {
                // 조회된 사용자 정보를 모델에 추가하여 뷰로 전달
                model.addAttribute("userInfo", dto);
            } else {
                // 사용자 정보가 없을 경우
                model.addAttribute("error", "사용자 정보를 찾을 수 없습니다.");
            }
        } else {
            // 세션에 userId가 없을 경우
            model.addAttribute("error", "로그인이 필요합니다.");
        }
    }
}
