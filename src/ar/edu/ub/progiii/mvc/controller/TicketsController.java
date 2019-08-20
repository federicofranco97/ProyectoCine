package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TicketsController {

    @Autowired
    ClientService clientService;

    @GetMapping("/tickets")
    public ModelAndView GetTickets(){
        ModelAndView model = new ModelAndView("Tickets");
        model.addObject("Tickets",clientService.GetAllTickets());
        return model;
    }
}
