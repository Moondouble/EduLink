package EduLink.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.StudentCommand;
import EduLink.domain.StudentDTO;
import EduLink.mapper.StudentMapper;

@Service
public class StudentUpdateService {
	@Autowired
	StudentMapper studentMapper;
	public void execute(StudentCommand studentCommand) {
		StudentDTO dto = new StudentDTO();
		dto.setStudentNum(studentCommand.getStudentNum());
		dto.setStudentName(studentCommand.getStudentName());
		dto.setStudentId(studentCommand.getStudentId());
		dto.setStudentEmail(studentCommand.getStudentEmail());
		dto.setStudentAddr(studentCommand.getStudentAddr());
		dto.setStudentAddrDetail(studentCommand.getStudentAddrDetail());
		dto.setStudentPost(studentCommand.getStudentPost());
		dto.setStudentPhone1(studentCommand.getStudentPhone1());
		dto.setStudentPhone2(studentCommand.getStudentPhone2());
		dto.setStudentBirth(studentCommand.getStudentBirth());
		//dto.setStudentImage(studentCommand.getStudentImage());
		studentMapper.studentUpdate(dto);
		
	}

}
