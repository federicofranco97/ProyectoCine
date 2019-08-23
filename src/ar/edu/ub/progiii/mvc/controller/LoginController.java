package ar.edu.ub.progiii.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ub.progiii.mvc.service.ClientService;

@Controller
public class LoginController {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/login")
	public ModelAndView GetLoginView() {
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	
	@PostMapping("/login_sent")
	public ModelAndView EmployeeLogin(@RequestParam("EmployeeId") String employeeId, @RequestParam("EmployeePass") String employeePass) {
		ModelAndView model = new ModelAndView();
		return model;
	}
}
