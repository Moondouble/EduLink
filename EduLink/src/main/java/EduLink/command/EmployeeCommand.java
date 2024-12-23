package EduLink.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmployeeCommand {
	String empNum;
	String empId;
	String empName;
	String empPw;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date empRegist;	
}
