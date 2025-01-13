package EduLink.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
	
	@GetMapping("buyItem")
	public String buyItem( // 바로구매할 상품을 장바구니에 넣고 결제정보 페이지로 이동하면 바로구매가 된다.
			@RequestParam(value="classNum") String classNum,
			Integer qty,
			HttpSession session,HttpServletResponse response) {
		// 먼저 장바구니에 넣는다. 
		String result = cartInsertService.execute(classNum, qty, session);
		if(result == "999") {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				String str = "<script>"
						+ "alert('관리자는 구매할 수 없습니다.');"
						+ "location.href='/class/classDetail?class="+classNum+"';" //장바구니에 안들어 갔으면 상품페이지
						+ "</script>";
				out.print(str);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(result == "000") {
			return "redirect:/"; //아니면 홈으로 
		}
		//정산적으로 처리 되었다면 결제정보 입력페이지로 이동하면 됩니다
		return "redirect:/purchase/classBuy?nums="+classNum;
	}
	
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
}
