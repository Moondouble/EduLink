package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.ClassroomDTO;

@Mapper
public interface ClassroomMapper
{
	public List<ClassroomDTO> classSelectAll();
	public List<ClassroomDTO> selectByTeacher(String teacherNum);
	public void classroomInsert(ClassroomDTO dto);
	public ClassroomDTO classSelectOne(String classNum);
	public void classUpdate(ClassroomDTO dto);
	public void classDelete(String classNum);
}
