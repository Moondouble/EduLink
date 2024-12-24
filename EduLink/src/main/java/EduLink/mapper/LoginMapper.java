package EduLink.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import EduLink.domain.AuthInfoDTO;

@Mapper
public interface LoginMapper {
	public String selectIdCheck(String userId);
	public String selectEmailCheck(String userEmail);
}








