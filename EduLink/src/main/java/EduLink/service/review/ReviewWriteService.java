package EduLink.service.review;

import org.springframework.stereotype.Service;

import EduLink.command.ReviewCommand;
import EduLink.domain.ReviewDTO;

@Service
public class ReviewWriteService
{
public void execute(ReviewCommand reviewCommand ) {
	ReviewDTO dto = new ReviewDTO();
	dto.setClassNum(reviewCommand.getClassNum());
	
	boardMapper.boardInsert(dto);
}
}
