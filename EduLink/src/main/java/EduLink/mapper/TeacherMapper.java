package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import EduLink.domain.StartEndPageDTO;
import EduLink.domain.TeacherDTO;

@Mapper
public interface TeacherMapper {
	public void teacherInsert(TeacherDTO dto);
	public List<TeacherDTO> teacherSelectAll(StartEndPageDTO sepDTO);
	public TeacherDTO teacherSelectOne(String teacherNum);
	public void teacherUpdate(TeacherDTO dto);
	public void teacherDelete(String teacherNum);
	public int teacherCount(String searchWord);
	public Integer teachersDelete(@Param("teachersNum") String teacherDels[]);
	
}