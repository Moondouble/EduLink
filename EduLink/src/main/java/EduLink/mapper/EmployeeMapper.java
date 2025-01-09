package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import EduLink.domain.EmployeeDTO;
import EduLink.domain.StartEndPageDTO;
import EduLink.domain.StudentDTO;

@Mapper
public interface EmployeeMapper {
	public void employeeInsert(EmployeeDTO dto);
	public List<EmployeeDTO> empSelectAll(StartEndPageDTO sepDTO);
	public void empDelete(String empNum);
	public int empCount(String searchWord);
	public Integer employeesDelete(@Param("employeesNum") String empDels[]);
}
