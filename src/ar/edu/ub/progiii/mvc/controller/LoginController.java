package ar.edu.ub.progiii.mvc.controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;

@Controller
public class LoginController {
	
	@Autowired
	ClientService clientService;
	Data data = new Data();
	Connection connection = new Connection();
	MappingTool map = new MappingTool();
	
	@GetMapping("/")
	public ModelAndView GetLoginView() {
		ModelAndView model = new ModelAndView("Login");
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
 		modelError.addObject("Contenido", Arrays.asList("Error","El usuario no pudo ser logueado, redireccionando a login!","/"));
		return modelError;
	}
}
