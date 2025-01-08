package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.PackageCommand;
import EduLink.service.AutoNumService;
import EduLink.service.packagee.PackageDetailService;
import EduLink.service.packagee.PackageListService;
import EduLink.service.packagee.PackageWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("package")
public class PackageController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	PackageWriteService packageWriteService;
	@Autowired
	PackageListService packageListService;
	
	
	@GetMapping("packageList")
	public String packageList(Model model) {
		packageListService.execute(model);
		return "thymeleaf/package/packageList";
	}
	@GetMapping("packageWrite")
	public String packageWrite(Model model, HttpSession session) {
		String autoNum = autoNumService.execute("package_", "package_num", 9, "package");
		PackageCommand packageCommand = new PackageCommand();
		packageCommand.setPackageNum(autoNum);
		
		model.addAttribute("packageCommand", packageCommand);
		return "thymeleaf/package/packageForm";
	}
	@PostMapping("packageRegist")
	public String Regist(PackageCommand packageCommand) {
		packageWriteService.execute(packageCommand);
		return "redirect:packageList";
	}
	
	@RequestMapping("packageDetail")
	public String packageDetail(@RequestParam("packageNum") String packageNum, Model model) {
		return "thymeleaf/package/packageInfo";
	}
}
