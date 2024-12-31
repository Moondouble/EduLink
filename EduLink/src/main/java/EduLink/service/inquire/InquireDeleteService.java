package EduLink.service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.BoardMapper;
import EduLink.mapper.InquireMapper;

@Service
public class InquireDeleteService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(String inquireNum) {
		inquireMapper.inquireDelete(inquireNum);
		
	}
}
