package EduLink.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NoticeCommand {
	String noticeNum;
	String empNum;
	String noticeName;
	String noticeContents;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date noticeDate;
}
