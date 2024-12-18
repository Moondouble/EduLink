package EduLink.command;

import java.util.Date;

import lombok.Data;

@Data
public class StudentCommand {
	// html에 전달할 오류 메세지를 command에서 설정해 준다.
	// String 자료형에서는 @NotEmpty, @NotBlank를 사용한다.
	String studentNum;
	String studentName;
	String studentId;
	String studentPw;
	String studentEmail;
	String studentEmailConf;
	Date studentRegist;
	Date studentBirth;
	String studentJumin;
	String memberPost;
	String studentAddr;
	String studentAddrDetail;
	String studentPost;
	String memberPhone1;
	String studentPhone1;
	String studentPhone2;
	String studentImage;
}
