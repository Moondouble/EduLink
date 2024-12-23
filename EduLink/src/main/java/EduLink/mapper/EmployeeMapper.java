package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.EmployeeDTO;
import EduLink.domain.StudentDTO;

@Mapper
public interface EmployeeMapper {
	public void employeeInsert(EmployeeDTO dto);
	public List<EmployeeDTO> empSelectAll();
	public void empDelete(String empNum);
}
