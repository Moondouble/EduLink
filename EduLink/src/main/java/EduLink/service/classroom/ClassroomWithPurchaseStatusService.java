package EduLink.service.classroom;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.AuthInfoDTO;
import EduLink.domain.ClassroomDTO;
import EduLink.domain.ClassroomDTOWithStatus;
import EduLink.domain.OrderListDTO;
import EduLink.mapper.ClassroomMapper;
import EduLink.mapper.PurchaseMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class ClassroomWithPurchaseStatusService {
    @Autowired
    ClassroomMapper classroomMapper;
    @Autowired
    PurchaseMapper purchaseMapper;

    public void execute(HttpSession session, Model model) {
        // 1. 세션에서 사용자 정보 가져오기
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        if (auth == null) {
            model.addAttribute("error", "로그인이 필요합니다.");
            return;
        }

        // 2. 구매한 강의 번호 리스트 가져오기
        List<OrderListDTO> orderList = purchaseMapper.orderList1(auth.getUserNum(), null);
        List<String> purchasedClassNums = orderList.stream()
            .flatMap(order -> order.getPurchaseListClassDTOs().stream())
            .map(purchase -> purchase.getClassNum())
            .collect(Collectors.toList());

        // 3. 전체 강의 목록 가져오기
        List<ClassroomDTO> allClasses = classroomMapper.classSelectAll();

        // 4. 구매 상태를 추가하여 새로운 리스트 생성
        List<ClassroomDTOWithStatus> classListWithStatus = allClasses.stream()
            .map(classroom -> {
                String purchaseStatus = purchasedClassNums.contains(classroom.getClassNum()) 
                    ? "구매 완료" 
                    : "구매 가능";
                return new ClassroomDTOWithStatus(classroom, purchaseStatus);
            })
            .collect(Collectors.toList());

        // 5. Model에 데이터 추가
        model.addAttribute("classList", classListWithStatus);
    }
}
