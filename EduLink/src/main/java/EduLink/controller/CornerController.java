package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import EduLink.service.corner.CartInsertService;
import EduLink.service.corner.CartListService;
import EduLink.service.corner.ClassCartDelService;
import EduLink.service.corner.ClassCartDelsService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("corner")
public class CornerController {
	@Autowired
	CartInsertService cartInsertService;
	@Autowired
	CartListService cartListService;
	@Autowired
	ClassCartDelsService classCartDelsService;
	@Autowired
	ClassCartDelService classCartDelService;
	
	@GetMapping("cartDel")
	public String cartDel(
			@RequestParam("classNum") String classNum,
			HttpSession session) {
		classCartDelService.execute(classNum, session);
		return "redirect:cartList";
	}
	
	@PostMapping(value = "cartDels")
	@ResponseBody
	public String cartdel(// javascript 배열을 받을 이름에 배열 표시를 해줘야합니다.
			@RequestParam("classNums[]") String classNums[], //배열이므로 배열로 받아오겠습니다.
			HttpSession session) {
		return classCartDelsService.execute(classNums, session);
	}
	
	
	@GetMapping("cartList")
	public String cartList(Model model, HttpSession session) {
		cartListService.execute(model, session);
		return "thymeleaf/corner/cartList";
	}
	
	@GetMapping("cartAdd")
	@ResponseBody
	public String cartAdd(
			@RequestParam(value="classNum") String classNum,
			Integer qty,
			HttpSession session) {
		return cartInsertService.execute(classNum, qty, session);
	}
	
	@GetMapping("buyItem")
	public String buyItem(@RequestParam("classNum")String classNum, HttpSession session, HttpServletResponse response) {
		return null;
	}
}
