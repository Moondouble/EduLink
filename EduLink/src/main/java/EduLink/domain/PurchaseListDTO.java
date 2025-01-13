package EduLink.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("purchaseListDTO")
public class PurchaseListDTO {
	String purchaseNum;
	String classNum;
	Integer purchaseQty;
	Integer totalPrice;
	
	String studentNum;
	String [] classNums;
}
