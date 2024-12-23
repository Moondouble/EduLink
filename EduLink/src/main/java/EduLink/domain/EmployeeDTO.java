package EduLink.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("employeeDTO")
public class EmployeeDTO {
	String empNum;
	String empId;
	String empName;
	String empPw;
	String empRegist;
}
