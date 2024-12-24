package EduLink.service.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.HomeworkCommand;
import EduLink.domain.HomeworkDTO;
import EduLink.mapper.HomeworkMapper;

@Service
public class HomeworkWriteService {
	@Autowired
	HomeworkMapper homeworkMapper;
	public void execute(HomeworkCommand homeworkCommand) {
		HomeworkDTO dto = new HomeworkDTO();
		dto.setClassNum(homeworkCommand.getClassNum());
		dto.setHwContents(homeworkCommand.getHwContents());
		dto.setHwEndDate(homeworkCommand.getHwEndDate());
		dto.setHwFeedbackNum(homeworkCommand.getHwFeedbackNum());
		dto.setHwName(homeworkCommand.getHwName());
		dto.setHwNum(homeworkCommand.getHwNum());
		dto.setTeacherNum(homeworkCommand.getTeacherNum());
		homeworkMapper.homeworkInsert(dto);
	}
}
