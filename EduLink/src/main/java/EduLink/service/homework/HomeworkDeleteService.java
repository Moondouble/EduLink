package EduLink.service.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.HomeworkMapper;
import EduLink.mapper.StudentMapper;

@Service
public class HomeworkDeleteService {
	@Autowired
	HomeworkMapper homeworkMapper;
	public void execute(String hwNum) {
		homeworkMapper.homeworkDelete(hwNum);
		
	}
	
}
