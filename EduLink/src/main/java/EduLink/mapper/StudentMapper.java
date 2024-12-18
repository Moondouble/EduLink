package EduLink.mapper;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.StudentDTO;

@Mapper
public interface StudentMapper {
	public void studentInsert(StudentDTO dto);
}
