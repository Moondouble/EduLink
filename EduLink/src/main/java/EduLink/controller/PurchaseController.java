package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.PurchaseCommand;
import EduLink.service.purchase.ClassBuyService;
import EduLink.service.purchase.ClassOrderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	ClassBuyService classBuyService;
	@Autowired
	ClassOrderService classOrderService;

	@PostMapping("classOrder")
	public String classOrder(PurchaseCommand purchaseCommand,HttpSession session, Model model,
			HttpServletResponse response) {
		String purchaseNum = classOrderService.execute(purchaseCommand,session, model);
		// paymentOk를 만들어 주겠습니다.
		return "redirect:paymentOk?purchaseNum="+purchaseNum;
	}
	
	@RequestMapping(value = "classBuy")
	public String goodsBuy(@RequestParam(value = "nums") String[] nums, // check박스가 배열로 전송된다.
			HttpSession session, Model model) {
		classBuyService.execute(nums, session, model);
		return "thymeleaf/purchase/classOrder";
	}
}
