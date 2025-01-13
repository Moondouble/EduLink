package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("purchaseDTO")
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {
	String purchaseNum; //
	Date purchaseDate;
	Integer purchasePrice; //
	String message; //
	String purchaseStatus; //
	String studentNum;
	
}
