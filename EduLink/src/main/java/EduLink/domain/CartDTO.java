package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("cartDTO")
public class CartDTO {
	String cartNum;
	String studentNum;
	String classNum;
	Integer cartQty;
	Date cartDate;
	String [] classNums;
}
