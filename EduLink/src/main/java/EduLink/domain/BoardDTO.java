package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("boardDTO")
public class BoardDTO {
	String boardNum;
	String classNum;
	String studentNum;
	String teacherNum;
	String writeNum;
	String boardName;
	String boardContents;
	String boardCategory;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date boardDate;
}
