package EduLink.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("purchaseListClassDTO")
public class PurchaseListClassDTO {
	// 구매한 각 상품마다 하나의 상품정보 : 구매한 각 상품마다 상품 정보를 가져와야 합니다.
	PurchaseListDTO purchaseListDTO;
	ClassroomDTO classroomDTO;
}
