package EduLink.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.TeacherCommand;
import EduLink.domain.TeacherDTO;
import EduLink.mapper.TeacherMapper;

@Service
public class TeacherWriteService {
	@Autowired
	TeacherMapper teacherMapper;
	public void execute(TeacherCommand teacherCommand) {
		TeacherDTO dto = new TeacherDTO();
		dto.setTeacherNum(teacherCommand.getTeacherNum());
		dto.setTeacherId(teacherCommand.getTeacherId());
		dto.setTeacherPw(teacherCommand.getTeacherPw());
		dto.setTeacherName(teacherCommand.getTeacherName());
		dto.setTeacherAddr(teacherCommand.getTeacherAddr());
		dto.setTeacherAddrDetail(teacherCommand.getTeacherAddrDetail());
		dto.setTeacherPost(teacherCommand.getTeacherPost());
		dto.setTeacherPhone1(teacherCommand.getTeacherPhone1());
		dto.setTeacherPhone2(teacherCommand.getTeacherPhone2());
		dto.setTeacherJumin(teacherCommand.getTeacherJumin());
		dto.setTeacherEmail(teacherCommand.getTeacherEmail());
		dto.setTeacherImage(teacherCommand.getTeacherImage());
		dto.setTeacherEmailConf(teacherCommand.getTeacherEmailConf());
		dto.setTeacherRegist(teacherCommand.getTeacherRegist());
		teacherMapper.teacherInsert(dto);
		
	}

}
