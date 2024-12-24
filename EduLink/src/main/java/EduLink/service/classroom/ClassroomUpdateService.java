package EduLink.service.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.ClassroomCommand;
import EduLink.domain.ClassroomDTO;
import EduLink.mapper.ClassroomMapper;

@Service
public class ClassroomUpdateService {
	@Autowired
	ClassroomMapper classroomMapper;
	public void execute(ClassroomCommand classroomCommand) {
		ClassroomDTO dto = new ClassroomDTO();
		dto.setClassNum(classroomCommand.getClassNum());
		dto.setTeacherNum(classroomCommand.getTeacherNum());
		dto.setClassName(classroomCommand.getClassName());
		dto.setClassContents(classroomCommand.getClassContents());
		dto.setClassStartDate(classroomCommand.getClassStartDate());
		dto.setClassEndDate(classroomCommand.getClassEndDate());
		dto.setClassWeek(classroomCommand.getClassWeek());
		classroomMapper.classUpdate(dto);
		
	}

}