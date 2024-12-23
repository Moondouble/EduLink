package EduLink.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TeacherCommand {
	String teacherNum;
	@NotEmpty(message = "아이디를 입력해 주세요.")
	String teacherId;
	@NotEmpty(message = "이름을 입력해 주세요.")
	String teacherName;
	@NotBlank(message = "이메일을 입력해 주세요.")
	String teacherEmail;
	String teacherEmailConf;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date teacherRegist;
	@NotBlank(message = "비밀번호를 입력해 주세요.")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
	message = "영문/숫자/특수문자 조합, 8글자 이상")
	String teacherPw;
	@NotBlank(message = "비밀번호 확인을 입력해 주세요.")
	String teacherPwCon;
	@NotBlank(message = "주소를 입력해 주세요.")
	String teacherAddr;
	String teacherAddrDetail;
	String teacherJumin;
	String teacherPost;
	@NotBlank(message = "휴대전화번호를 입력해 주세요.")
	String teacherPhone1;
	String teacherPhone2;
	String teacherImage;
	
	public boolean isTeacherPwEqualTeacherPwCon() {
		return teacherPw.equals(teacherPwCon);
	}
	
	
	
	
}
