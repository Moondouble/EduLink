package EduLink.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.TeacherCommand;
import EduLink.domain.StudentDTO;
import EduLink.domain.TeacherDTO;
import EduLink.mapper.TeacherMapper;

@Service
public class TeacherUpdateService {
	@Autowired
	TeacherMapper teacherMapper;
	public void execute(TeacherCommand teacherCommand) {
		TeacherDTO dto = new TeacherDTO();
		dto.setTeacherNum(teacherCommand.getTeacherNum());
		dto.setTeacherId(teacherCommand.getTeacherId());
		dto.setTeacherName(teacherCommand.getTeacherName());
		dto.setTeacherAddr(teacherCommand.getTeacherAddr());
		dto.setTeacherAddrDetail(teacherCommand.getTeacherAddrDetail());
		dto.setTeacherPost(teacherCommand.getTeacherPost());
		dto.setTeacherPhone1(teacherCommand.getTeacherPhone1());
		dto.setTeacherPhone2(teacherCommand.getTeacherPhone2());
		dto.setTeacherEmail(teacherCommand.getTeacherEmail());
		//dto.setTeacherImage(teacherCommand.getTeacherImage());
		teacherMapper.teacherUpdate(dto);
		
	}
	
}
