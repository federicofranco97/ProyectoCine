package ar.edu.ub.progiii.mvc.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
			model.addObject("date",clientService.GetDateToday());
			return model;
		}
		ModelAndView modelError = new ModelAndView("ErrorPage");
 		modelError.addObject("Contenido", Arrays.asList("Error","El usuario no tiene acceso a esta pagina, redireccionando a login!","/"));
		return modelError;
	}
	
	/**
	 * Suma a la fecha de la pagina un dia, de no poderse activa un mensaje de aviso
	 * @return
	 */
	@GetMapping("/sumar_fecha")
	public ModelAndView GetAddDate(@RequestParam("datePage") String datePage) {
		ModelAndView model = new ModelAndView("OnsiteSale");
		if(clientService.CanDaysBeAdded(datePage)) {
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetAllShows());
	        model.addObject("date",clientService.AddDays(datePage, 1));
	        return model;
		}
		model.addObject("films",clientService.GetAllFilms());
		model.addObject("shows" ,clientService.GetAllShows());
		model.addObject("date",datePage);
		model.addObject("Content", Arrays.asList("Aviso","No hay mas fechas disponibles!","1"));
		return model;
	}
}
