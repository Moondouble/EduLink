package EduLink.service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.ReviewDTO;
import EduLink.mapper.ReviewMapper;

@Service
public class ReviewListService {
	@Autowired
	ReviewMapper reviewMapper;

	public void execute(String classNum, Model model) {
		List<ReviewDTO> list = reviewMapper.reviewSelectAll(classNum);
		model.addAttribute("list", list);
	}
	
	public void execute(Model model) {
		List<ReviewDTO> list = reviewMapper.reviewSelectAllWithoutClass();
        model.addAttribute("list2", list);
	}
}
