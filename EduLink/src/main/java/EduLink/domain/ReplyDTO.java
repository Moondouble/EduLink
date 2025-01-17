package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("replyDTO")
public class ReplyDTO
{
	String replyNum;
	String boardNum;
	String teacherNum;
	String replyContents;
	Date replyDate;   
}
