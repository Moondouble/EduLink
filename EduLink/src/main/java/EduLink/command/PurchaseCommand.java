package EduLink.command;

import lombok.Data;

@Data
public class PurchaseCommand {
	Integer sumTotalPrice;
	Integer sumPrice;
	String classNums;
	String message;
}
