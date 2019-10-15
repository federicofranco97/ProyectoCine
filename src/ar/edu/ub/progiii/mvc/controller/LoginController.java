package ar.edu.ub.progiii.mvc.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.dto.RateCategoryDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.repository.querys.QueryStoredProcedureWResponse;
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
	/**
	Metodo que te lleva a la vista para logearte
	 * @throws SQLException 
	 */
	@GetMapping("/")
	public ModelAndView GetLoginView() throws SQLException {
		ModelAndView model = new ModelAndView("Login");
		clientService.UpdateLoginStatus();
		clientService.ClearCurrentUser();
		return model;
	}
	/**
	Metodo que, en el caso de ingresar las credenciales correctas te lleva el menu
	sino, te lleva a la página de Error
	 */
	@PostMapping("/login_sent")
	public ModelAndView EmployeeLogin(@RequestParam("EmployeeId") String employeeId, @RequestParam("EmployeePass") String employeePass) {
		RedirectView redirectView = new RedirectView("/menu");
		redirectView.setExposePathVariables(false);
		try {
			if(clientService.verifyEmployeeLogin(employeeId, employeePass)) {
				return new ModelAndView(redirectView);
			}
		} catch (Exception e) {
			ModelAndView modelError = new ModelAndView("ErrorPage");
	 		modelError.addObject("Contenido", Arrays.asList("Error","El usuario no pudo ser logueado, redireccionando a login!","/"));
			return modelError;
		}
		ModelAndView modelError = new ModelAndView("ErrorPage");
 		modelError.addObject("Contenido", Arrays.asList("Error","El usuario no pudo ser logueado, redireccionando a login!","/"));
		return modelError;
	}
}
