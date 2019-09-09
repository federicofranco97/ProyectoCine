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
	
	@SuppressWarnings("static-access")
	@GetMapping("/cambio_clave")
	public ModelAndView GetPassView() {
		int employeeNumber = clientService.currentEmployee.getEmployeeNumber();
		if(clientService.IsEmployeeAlowed(employeeNumber)) {
			ModelAndView model = new ModelAndView("clave");
			model.addObject("currentID",employeeNumber);
			return model;
		}
		ModelAndView modelError = new ModelAndView("ErrorPage");
 		modelError.addObject("Contenido", Arrays.asList("Error","El usuario no tiene acceso a esta pagina, redireccionando a login!","/"));
		return modelError;
		
	}
	
	@PostMapping("/pass_sent")
	public ModelAndView changePass(@RequestParam("EmployeeId") String employeeId, @RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass) {
		int aux = clientService.changePass(employeeId, oldPass, newPass);
		if(aux == 1) {
			ModelAndView modelSucces = new ModelAndView("clave");
			modelSucces.addObject("Contenido", Arrays.asList("Exito","La clave ha sido cambiada con exito!","1"));
			return modelSucces;
		}
		if(aux == 2) {
			ModelAndView modelError = new ModelAndView("ErrorPage");
	 		modelError.addObject("Contenido", Arrays.asList("Error","Clave incorrecta!","/clave"));
			return modelError;
		}
			ModelAndView modelError = new ModelAndView("ErrorPage");
	 		modelError.addObject("Contenido", Arrays.asList("Error","Clave incorrecta, 3 intentos fallidos, usuario baneado!","/"));
			return modelError;
	}
}
