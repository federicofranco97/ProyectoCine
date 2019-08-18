package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WithdrawController {

	@Autowired
	ClientService clientService;

	@GetMapping("/")
	public ModelAndView GetView() {
		ModelAndView model = new ModelAndView("Alivio");
		//Test de traer un cliente de bd
		clientService.GetAllClients();
		return model;
	}

}
