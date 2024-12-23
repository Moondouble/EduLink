package EduLink.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentCommand {
	// html에 전달할 오류 메세지를 command에서 설정해 준다.
	// String 자료형에서는 @NotEmpty, @NotBlank를 사용한다.
	String studentNum;
	@NotEmpty(message = "이름을 입력해 주세요.")
	String studentName;
	@NotEmpty(message = "아이디를 입력해 주세요.")
	String studentId;
	@NotBlank(message = "비밀번호를 입력해 주세요.")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
	message = "영문/숫자/특수문자 조합, 8글자 이상")
	String studentPw;
	@NotBlank(message = "비밀번호 확인을 입력해 주세요.")
	String studentPwCon;
	@NotBlank(message = "이메일을 입력해 주세요.")
	String studentEmail;
	String studentEmailConf;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date studentRegist;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date studentBirth;
	String studentJumin;
	@NotBlank(message = "주소를 입력해 주세요.")
	String studentAddr;
	String studentAddrDetail;
	String studentPost;
	@NotBlank(message = "휴대전화번호를 입력해 주세요.")
	String studentPhone1;
	String studentPhone2;
	String studentImage;
	
	public boolean isStudentPwEqualStudentPwCon() {
		return studentPw.equals(studentPwCon);
	}
}