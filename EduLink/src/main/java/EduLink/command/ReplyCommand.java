package EduLink.command;

import java.util.Date;

import lombok.Data;
@Data
public class ReplyCommand
{
	String replyNum;
	String boardNum;
	String teacherNum;
	String replyContents;
	Date replyDate;   
}
