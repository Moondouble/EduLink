package EduLink.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Alias("classroomDTOWithStatus")
public class ClassroomDTOWithStatus {
    private ClassroomDTO classroomDTO;
    private String purchaseStatus; // "구매 완료" 또는 "구매 가능"
}
