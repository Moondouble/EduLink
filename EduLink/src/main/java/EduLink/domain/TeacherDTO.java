package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("teacherDTO")
public class TeacherDTO {
	String teacherNum;
	String teacherId;
	String teacherName;
	String teacherEmail;
	String teacherEmailConf;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date teacherRegist;
	String teacherPw;
	String teacherPwCon;
	String teacherAddr;
	String teacherAddrDetail;
	String teacherJumin;
	String teacherPost;
	String teacherPhone1;
	String teacherPhone2;
	String teacherImage;
}
