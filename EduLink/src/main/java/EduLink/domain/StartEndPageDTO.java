package EduLink.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("startEndPageDTO")
public class StartEndPageDTO {
	int startRow;
	int endRow;
	String classNum;
	String searchWord;
}