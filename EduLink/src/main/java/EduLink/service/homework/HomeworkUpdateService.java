package EduLink.service.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.HomeworkCommand;
import EduLink.command.StudentCommand;
import EduLink.domain.HomeworkDTO;
import EduLink.domain.StudentDTO;
import EduLink.mapper.HomeworkMapper;
import EduLink.mapper.StudentMapper;

@Service
public class HomeworkUpdateService {
	@Autowired
	HomeworkMapper homeworkMapper;
	public void execute(HomeworkCommand homeworkCommand) {
		HomeworkDTO dto = new HomeworkDTO();
		dto.setHwNum(homeworkCommand.getHwNum());
		dto.setHwContents(homeworkCommand.getHwContents());
		dto.setHwEndDate(homeworkCommand.getHwEndDate());
		dto.setHwName(homeworkCommand.getHwName());
		homeworkMapper.homeworkUpdate(dto);
		
	}
}
