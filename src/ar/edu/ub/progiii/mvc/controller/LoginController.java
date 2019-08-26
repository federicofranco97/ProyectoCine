package ar.edu.ub.progiii.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;

@Controller
public class LoginController {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/")
	public ModelAndView GetLoginView() {
		ModelAndView model = new ModelAndView("login");
		clientService.ClearCurrentUser();
		return model;
	}
	
	@PostMapping("/login_sent")
	public ModelAndView EmployeeLogin(@RequestParam("EmployeeId") String employeeId, @RequestParam("EmployeePass") String employeePass) {
		RedirectView redirectView = new RedirectView("/menu");
		redirectView.setExposePathVariables(false);
		boolean aux = clientService.verifyEmployeeLogin(employeeId, employeePass);
		if(aux) {
			return new ModelAndView(redirectView);
		}
 		ModelAndView modelError = new ModelAndView("ErrorPage");
		modelError.addObject("Contenido", Arrays.asList("Error","Ocurrio un error al realizar el log in"));
		return modelError;
	}
}
