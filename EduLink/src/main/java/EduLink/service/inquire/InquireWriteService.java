package EduLink.service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.InquireCommand;
import EduLink.domain.InquireDTO;
import EduLink.mapper.InquireMapper;

@Service
public class InquireWriteService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(InquireCommand inquireCommand) {
		InquireDTO dto = new InquireDTO();
		dto.setEmpNum(inquireCommand.getEmpNum());
		dto.setInquireAnswer(inquireCommand.getInquireAnswer());
		dto.setInquireAnswerDate(inquireCommand.getInquireAnswerDate());
		dto.setInquireCategory(inquireCommand.getInquireCategory());
		dto.setInquireContents(inquireCommand.getInquireContents());
		dto.setInquireDate(inquireCommand.getInquireDate());
		dto.setInquireName(inquireCommand.getInquireName());
		dto.setInquireNum(inquireCommand.getInquireNum());
		dto.setStudentNum(inquireCommand.getStudentNum());
		dto.setInquireEmail(inquireCommand.getInquireEmail());
		if (inquireCommand.getTeacherNum() == null) {
		    dto.setTeacherNum("");
		}else {
			dto.setTeacherNum(inquireCommand.getTeacherNum());
		}
		if (inquireCommand.getStudentNum() == null) {
		    dto.setStudentNum("");
		}else {
			dto.setStudentNum(inquireCommand.getStudentNum());
		}
		if (inquireCommand.getEmpNum() == null) {
		    dto.setEmpNum("");
		}else {
			dto.setEmpNum(inquireCommand.getEmpNum());
		}
		if (inquireCommand.getInquireWriter() == null) {
			dto.setInquireWriter("");
		}else {
			dto.setInquireWriter(inquireCommand.getInquireWriter());
		}
		
		inquireMapper.inquireInsert(dto);
	}
}
