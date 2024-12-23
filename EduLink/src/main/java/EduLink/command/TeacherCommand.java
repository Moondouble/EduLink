package EduLink.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TeacherCommand {
	String teacherNum;
	String teacherId;
	String teacherName;
	String teacherEmail;
	String teacherEmailConf;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date teacherRegist;
	String teacherPw;
	String teacherAddr;
	String teacherAddrDetail;
	String teacherJumin;
	String teacherPost;
	String teacherPhone1;
	String teacherPhone2;
	String teacherImage;
}
