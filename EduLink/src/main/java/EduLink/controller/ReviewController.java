package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.ReviewCommand;
import EduLink.service.AutoNumService;
import EduLink.service.review.ReviewDeleteService;
import EduLink.service.review.ReviewDetailService;
import EduLink.service.review.ReviewListService;
import EduLink.service.review.ReviewUpdateService;
import EduLink.service.review.ReviewWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("review")
public class ReviewController
{
	@Autowired
	ReviewListService reviewListService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	ReviewWriteService reviewWriteService;
	@Autowired
	ReviewDetailService reviewDetailService;
	@Autowired
	ReviewUpdateService	 reviewUpdateService;
	@Autowired
	ReviewDeleteService reviewDeleteService;
	@GetMapping("reviewList")
	public String reviewList(@RequestParam("classNum")String classNum,Model model) {
		ReviewCommand reviewCommand = new ReviewCommand();
		reviewCommand.setClassNum(classNum);
		model.addAttribute("reviewCommand", reviewCommand);
		reviewListService.execute(classNum,model);
		return "thymeleaf/review/reviewList";
	}
	@GetMapping("reviewWrite")
	public String reviewWrite(@RequestParam("classNum")String classNum,Model model, HttpSession session) {
		String autoNum = autoNumService.execute("review_", "review_num", 7, "review");
		ReviewCommand reviewCommand = new ReviewCommand();
		reviewCommand.setReviewNum(autoNum);
		reviewCommand.setClassNum(classNum);
		model.addAttribute("reviewCommand", reviewCommand);
		return "thymeleaf/review/reviewForm";
	}
	
	@PostMapping("reviewWrite")
	public String reviewRegist(ReviewCommand reviewCommand) {
		reviewWriteService.execute(reviewCommand);
		return "redirect:reviewList?classNum="+reviewCommand.getClassNum();
	}
	
	@GetMapping("reviewDetail")
	public String reviewDetail(@RequestParam("reviewNum")String reviewNum,Model model) {
		reviewDetailService.execute(reviewNum,model);
		return "thymeleaf/review/reviewInfo";
	}
	
	@GetMapping("reviewUpdate")
	public String reviewModify(@RequestParam("reviewNum")String reviewNum,Model model) {
		reviewDetailService.execute(reviewNum, model);
		return "thymeleaf/review/reviewModify";
	}
	@PostMapping("reviewUpdate")
	public String reviewPUpdate(ReviewCommand reviewCommand) {
		reviewUpdateService.execute(reviewCommand);
		return "redirect:reviewList?classNum="+reviewCommand.getClassNum();
	}
	@PostMapping("reviewDelete")
	public String reviewDelete(@RequestParam("classNum")String classNum,@RequestParam("reviewNum")String reviewNum) {
		reviewDeleteService.execute(reviewNum);
		return "redirect:reviewList?classNum="+classNum;
	}
}
