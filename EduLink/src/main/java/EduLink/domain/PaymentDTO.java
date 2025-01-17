package EduLink.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("paymentDTO")
public class PaymentDTO {
	String purchaseNum;
	String confirmNumber;
	String cardNum;
	String tid;
	String total_Price;
	String resultMessage;
	String payMethod;
	String applDate;
	String applTime;
	String purchaseName;
}
