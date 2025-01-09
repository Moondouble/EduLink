package EduLink.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.EmployeeMapper;

@Service
public class EmployeesDeleteService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(String empDels []) {
		employeeMapper.employeesDelete(empDels);
	}


}