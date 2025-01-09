package EduLink.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.StudentMapper;

@Service
public class StudentsDeleteService {
	@Autowired
	StudentMapper studentMapper;
	public void execute(String studentDels []) {
		studentMapper.studentsDelete(studentDels);
	}

}
