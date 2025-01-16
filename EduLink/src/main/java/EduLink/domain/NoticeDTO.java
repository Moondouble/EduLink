package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("noticeDTO")
public class NoticeDTO {
	String noticeNum;
	String empNum;
	String noticeName;
	String noticeContents;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date noticeDate;
}
