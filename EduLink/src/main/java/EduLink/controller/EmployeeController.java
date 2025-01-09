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
import EduLink.service.employee.EmployeesDeleteService;
import EduLink.service.teacher.TeachersDeleteService;

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
	
	@Autowired
	EmployeesDeleteService employeesDeleteService;
	
	@PostMapping("empsDelete")
    public String empsDelete(
    		@RequestParam(value="empDels") String empDels []) {
    	employeesDeleteService.execute(empDels);
    	return "redirect:empList";
    }

	
	@GetMapping("empList")
	public String empList(
			@RequestParam(value="page", required = false, defaultValue = "1") int page,
    		@RequestParam(value="searchWord" , required=false) String searchWord,
			Model model) {
		employeeListService.execute(searchWord, page, model);
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
