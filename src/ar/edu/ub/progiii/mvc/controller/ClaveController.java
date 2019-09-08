package ar.edu.ub.progiii.mvc.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

@Controller
public class ClaveController {
	
	@Autowired
	ClientService clientService;
	Data data = new Data();
	Connection connection = new Connection();
	MappingTool map = new MappingTool();
	
	@GetMapping("/cambio_clave")
	public ModelAndView GetPassView() {
		ModelAndView model = new ModelAndView("clave");
		model.addObject("currentID",clientService.currentEmployee.getEmployeeNumber());
		return model;
	}
	
	/*@PostMapping("/login_sent")
	public ModelAndView EmployeeLogin(@RequestParam("EmployeeId") String employeeId, @RequestParam("EmployeePass") String employeePass) {
		RedirectView redirectView = new RedirectView("/menu");
		redirectView.setExposePathVariables(false);
		boolean aux = clientService.verifyEmployeeLogin(employeeId, employeePass);
		if(aux) {
			return new ModelAndView(redirectView);
		}
 		ModelAndView modelError = new ModelAndView("ErrorPage");
 		modelError.addObject("Contenido", Arrays.asList("Error","El usuario no pudo ser logueado, redireccionando a login!"));
		return modelError;
	}*/
}
