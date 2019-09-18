package ar.edu.ub.progiii.mvc.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

@Controller
public class OnsiteSaleController {
	@Autowired
	ClientService clientService;
	Data data = new Data();
	Connection connection = new Connection();
	MappingTool map = new MappingTool();
		
	/**
	 * Llama a la vista venta presencial, sus peliculas y funciones
	 * @return
	 */
	@GetMapping("/venta_presencial")
	public ModelAndView GetOnsiteSaleView() {
		int employeeNumber = clientService.currentEmployee.getEmployeeNumber();
		if(clientService.IsEmployeeAlowed(employeeNumber)) {
			ModelAndView model = new ModelAndView("OnsiteSale");
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetShowsByHour());
			//model.addObject("date",clientService.gets);
			return model;
		}
		ModelAndView modelError = new ModelAndView("ErrorPage");
 		modelError.addObject("Contenido", Arrays.asList("Error","El usuario no tiene acceso a esta pagina, redireccionando a login!","/"));
		return modelError;
	}
}
