package EduLink.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.ReviewCommand;
import EduLink.domain.ReviewDTO;
import EduLink.mapper.ReviewMapper;


@Service
public class ReviewUpdateService
{
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(ReviewCommand reviewCommand) {
		
		ReviewDTO dto = new ReviewDTO();
		dto.setReviewNum(reviewCommand.getReviewNum());
		dto.setReviewName(reviewCommand.getReviewName());
		dto.setReviewContents(reviewCommand.getReviewContents());
		dto.setReviewRate(reviewCommand.getReviewRate());
		reviewMapper.reviewUpdate(dto);
	}
}
