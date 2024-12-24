package EduLink.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("userInfoDTO")
public class UserInfoDTO
{
String userNum;
String userId;
String userPw;
String userEmail;
String userName;
String userPhone;
String userImage;
String userRegist;
}
