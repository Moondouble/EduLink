package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Data
@Alias("reviewDTO")
public class ReviewDTO
{
	String reviewNum;
	String classNum;
	String studentNum;
	String reviewName;
	String reviewContents;
	String reviewRate;
	Date reviewDate;
}
