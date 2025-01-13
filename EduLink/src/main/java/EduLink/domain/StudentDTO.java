package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("studentDTO")
public class StudentDTO {
	String studentNum;
	String studentName;
	String studentId;
	String studentPw;
	String studentEmail;
	String studentEmailConf;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date studentRegist;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date studentBirth;
	String studentJumin;
	String studentAddr;
	String studentAddrDetail;
	String studentPost;
	String studentPhone1;
	String studentPhone2;
	String studentImage;
	String studentStoreImage;
}