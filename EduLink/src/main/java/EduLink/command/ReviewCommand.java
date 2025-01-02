package EduLink.command;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class ReviewCommand
{
	String reviewNum;
	String classNum;
	String studentNum;
	String reviewName;
	String reviewContents;
	String reviewRate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date reviewDate;
}
