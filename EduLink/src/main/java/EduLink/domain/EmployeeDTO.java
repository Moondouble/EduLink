package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("employeeDTO")
public class EmployeeDTO {
	String empNum;
	String empId;
	String empName;
	String empPw;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date empRegist;
}
