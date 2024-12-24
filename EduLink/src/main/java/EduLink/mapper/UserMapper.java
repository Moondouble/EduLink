package EduLink.mapper;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.AuthInfoDTO;
import EduLink.domain.StudentDTO;


@Mapper
public interface UserMapper {
	public int userInsert(StudentDTO dto);
	public AuthInfoDTO loginSelect(String userId);
}
