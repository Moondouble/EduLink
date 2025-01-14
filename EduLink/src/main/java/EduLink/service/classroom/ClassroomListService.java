package EduLink.service.classroom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.ClassroomDTO;
import EduLink.domain.TeacherDTO;
import EduLink.mapper.ClassroomMapper;
import EduLink.mapper.TeacherMapper;

@Service
public class ClassroomListService
{
	@Autowired
	ClassroomMapper classroomMapper;
	@Autowired
	TeacherMapper teacherMapper;
	
	public TeacherDTO executeByTeacherNum(Model model, String teacherNum) {
        List<ClassroomDTO> list = classroomMapper.selectByTeacher(teacherNum);
        model.addAttribute("list", list);
        return teacherMapper.teacherSelectOne(teacherNum);
    }
    
    // teacherNum이 없을 경우 모든 클래스 반환
    public void execute(Model model) {
        List<ClassroomDTO> list = classroomMapper.classSelectAll();
        model.addAttribute("list", list);
    }
}
