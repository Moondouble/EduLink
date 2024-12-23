package EduLink.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardCommand {
	String boardNum;
	String classNum;
	String studentNum;
	String teacherNum;
	String writeNum;
	String boardName;
	String boardContents;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date boardDate;
}
