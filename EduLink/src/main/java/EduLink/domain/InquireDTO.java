package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("inquireDTO")
public class InquireDTO {
	String inquireNum;
	String studentNum;
	String empNum;
	String teacherNum;
	String inquireName;
	String inquireCategory;
	String inquireContents;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date inquireDate;
	String inquireAnswer;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date inquireAnswerDate;
	
}
