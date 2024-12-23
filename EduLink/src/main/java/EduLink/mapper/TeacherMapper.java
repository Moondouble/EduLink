package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.TeacherDTO;

@Mapper
public interface TeacherMapper {
	public void teacherInsert(TeacherDTO dto);
	public List<TeacherDTO> teacherSelectAll();
	public TeacherDTO teacherSelectOne(String teacherNum);
	public void teacherUpdate(TeacherDTO dto);
	public void teacherDelete(String teacherNum);
	
}
