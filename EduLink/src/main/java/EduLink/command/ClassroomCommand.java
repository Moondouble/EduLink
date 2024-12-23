package EduLink.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class ClassroomCommand
{
	String classNum;
	String teacherNum;
	String className;
	String classContents;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date classStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date classEndDate;
	Integer classWeek;

}
