package EduLink.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("cartGoodsDTO")
public class CartGoodsDTO {
	CartDTO cartDTO;
	ClassroomDTO classroomDTO;
	Integer totalPrice;
}
