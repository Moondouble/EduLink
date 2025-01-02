package EduLink.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.ReviewMapper;
@Service
public class ReviewDeleteService
{
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(String reviewNum) {
		reviewMapper.reviewDelete(reviewNum);
		
	}
}