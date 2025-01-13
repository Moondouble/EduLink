package EduLink.mapper;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.UserInfoDTO;

@Mapper
public interface MyPageMapper {
	public UserInfoDTO userSelectOne(String userId);
}
