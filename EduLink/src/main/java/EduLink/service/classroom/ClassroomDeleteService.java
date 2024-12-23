package EduLink.service.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.ClassroomMapper;

@Service
public class ClassroomDeleteService {
	@Autowired
	ClassroomMapper classroomMapper;
	public void execute(String classNum) {
		classroomMapper.classDelete(classNum);
	}
	
}
