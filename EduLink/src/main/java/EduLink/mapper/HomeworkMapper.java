package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.HomeworkDTO;
import EduLink.domain.StudentDTO;

@Mapper
public interface HomeworkMapper {
	public void homeworkInsert(HomeworkDTO dto);
	public List<HomeworkDTO> homeworkSelectAll(String classNum);
	public HomeworkDTO homeworkSelectOne(String hwNum);
	public void homeworkUpdate(HomeworkDTO dto);
	public void homeworkDelete(String hwNum);
}
