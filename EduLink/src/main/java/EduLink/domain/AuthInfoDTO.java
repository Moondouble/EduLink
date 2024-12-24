package EduLink.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("authInfoDTO")
public class AuthInfoDTO {
	String userId;
	String userPw;
	String userName;
	String grade;
	String userEmail;
}

