package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import EduLink.domain.StartEndPageDTO;
import EduLink.domain.StudentDTO;

@Mapper
public interface StudentMapper {
	public void studentInsert(StudentDTO dto);
	public List<StudentDTO> studentSelectAll(StartEndPageDTO sepDTO);
	public StudentDTO studentSelectOne(String studentNum);
	public void studentUpdate(StudentDTO dto);
	public void studentDelete(String studentNum);
	public int studentCount(String searchWord);
	public Integer studentsDelete(@Param("studentsNum") String studentDels []);
}
