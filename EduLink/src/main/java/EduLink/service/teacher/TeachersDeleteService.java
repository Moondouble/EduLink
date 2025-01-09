package EduLink.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.TeacherMapper;

@Service
public class TeachersDeleteService {
	@Autowired
	TeacherMapper teacherMapper;
	public void execute(String teacherDels []) {
		teacherMapper.teachersDelete(teacherDels);
	}

}
