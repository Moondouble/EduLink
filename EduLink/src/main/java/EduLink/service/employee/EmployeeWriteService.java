package EduLink.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.EmployeeCommand;
import EduLink.domain.EmployeeDTO;
import EduLink.mapper.EmployeeMapper;

@Service
public class EmployeeWriteService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpNum(employeeCommand.getEmpNum());
		dto.setEmpId(employeeCommand.getEmpId());
		dto.setEmpPw(employeeCommand.getEmpPw());
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpRegist(employeeCommand.getEmpRegist());
		employeeMapper.employeeInsert(dto);
	}
}
