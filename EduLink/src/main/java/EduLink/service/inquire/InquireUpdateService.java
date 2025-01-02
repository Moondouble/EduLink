package EduLink.service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.InquireCommand;
import EduLink.domain.InquireDTO;
import EduLink.mapper.InquireMapper;

@Service
public class InquireUpdateService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(InquireCommand inquireCommand) {
		
		InquireDTO dto = new InquireDTO();
		dto.setInquireNum(inquireCommand.getInquireNum());
		dto.setInquireCategory(inquireCommand.getInquireCategory());
		dto.setInquireName(inquireCommand.getInquireName());
		dto.setInquireContents(inquireCommand.getInquireContents());
		 
		inquireMapper.inquireUpdate(dto);
		
	}
}
