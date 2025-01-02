package EduLink.service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.BoardDTO;
import EduLink.domain.InquireDTO;
import EduLink.mapper.BoardMapper;
import EduLink.mapper.InquireMapper;

@Service
public class InquireDetailService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(String inquireNum, Model model) {
		InquireDTO dto = inquireMapper.inquireSelectOne(inquireNum);
		model.addAttribute("inquireCommand", dto);
	}
}
