package EduLink.service.inquire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.InquireDTO;
import EduLink.mapper.InquireMapper;

@Service
public class InquireListService {
	@Autowired
	InquireMapper inquireMapper;
	
	public void execute(Model model) {
		List<InquireDTO> list = inquireMapper.inquireSelectAll();
		model.addAttribute("list", list);
	}
}
