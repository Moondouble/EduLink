package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import EduLink.service.review.ReviewListService;

@Controller
@RequestMapping("review")
public class ReviewController
{
	@Autowired
	ReviewListService reviewListService;
	@GetMapping("reviewList")
	public String reviewList(Model model) {
		reviewListService.execute(model);
		return "thymeleaf/review/reviewList";
	}
}
