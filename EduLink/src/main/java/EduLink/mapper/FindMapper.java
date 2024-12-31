package EduLink.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FindMapper {
	  // 아이디 찾기
    public String findId(@Param("userName") String userName,
                         @Param("userEmail") String userEmail);

 // 비밀번호 찾기
    public String findPw(@Param("userId") String userId,
                         @Param("userName") String userName,
                         @Param("userEmail") String userEmail);
}
