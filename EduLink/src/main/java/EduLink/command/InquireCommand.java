package EduLink.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class InquireCommand {
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
	
	String writer;
}
