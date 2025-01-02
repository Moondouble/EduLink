package EduLink.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.ReviewDTO;
import EduLink.mapper.ReviewMapper;
@Service
public class ReviewDetailService
{
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(String reviewNum, Model model) {
		ReviewDTO dto = reviewMapper.reviewSelectOne(reviewNum);
		model.addAttribute("reviewCommand", dto);
	}

}
