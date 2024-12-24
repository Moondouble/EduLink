package EduLink.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class HomeworkCommand {
	String hwNum;
	String classNum;
	String teacherNum;
	String hwName;
	String hwContents;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date hwEndDate;
	String hwFeedbackNum;
}
