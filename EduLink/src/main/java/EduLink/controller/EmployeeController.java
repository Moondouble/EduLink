package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.command.EmployeeCommand;
import EduLink.service.AutoNumService;
import EduLink.service.employee.EmployeeDeleteService;
import EduLink.service.employee.EmployeeListService;
import EduLink.service.employee.EmployeeWriteService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	EmployeeWriteService employeeWriteService;
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeDeleteService employeeDeleteService;
	
	@GetMapping("empList")
	public String empList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/empList";
	}
	
	@GetMapping("empWrite")
	public String studentWrite(Model model) {
		String autoNum = autoNumService.execute("emp_", "emp_num", 5, "employee");
		EmployeeCommand employeeCommand = new EmployeeCommand();
		employeeCommand.setEmpNum(autoNum);
		model.addAttribute("employeeCommand", employeeCommand);
		return "thymeleaf/employee/empForm";
	}
	@PostMapping("empRegist")
	public String empRegist(EmployeeCommand employeeCommand) {
		employeeWriteService.execute(employeeCommand);
		return "redirect:empList";
	}
	
	@GetMapping("empDelete")
	public String empDelete(@RequestParam("empNum") String empNum) {
		employeeDeleteService.execute(empNum);
		return "redirect:/";
	}
}
