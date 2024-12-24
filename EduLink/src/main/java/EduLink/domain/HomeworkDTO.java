package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("homeworkDTO")
public class HomeworkDTO {
	String hwNum;
	String classNum;
	String teacherNum;
	String hwName;
	String hwContents;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date hwEndDate;
	String hwFeedbackNum;
}
