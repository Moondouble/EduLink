package EduLink.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReviewCommand
{
	String classNum;
	String studentNum;
	String reviewName;
	String reviewContents;
	String reviewRate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date reviewDate;
}
