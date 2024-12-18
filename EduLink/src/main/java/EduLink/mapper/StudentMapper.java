package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.StudentDTO;

@Mapper
public interface StudentMapper {
	public void studentInsert(StudentDTO dto);
	public List<StudentDTO> studentSelectAll();
	public StudentDTO studentSelectOne(String studentNum);
	public void studentUpdate(StudentDTO dto);
}
