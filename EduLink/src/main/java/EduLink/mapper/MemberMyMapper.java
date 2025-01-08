package EduLink.mapper;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.StudentDTO;

@Mapper
public interface MemberMyMapper {
	public StudentDTO studentInfo(String stuentId);
}
