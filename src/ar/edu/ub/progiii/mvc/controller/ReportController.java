package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

    @Autowired
    ClientService clientService;

    @GetMapping("/ventas_dia")
    public ModelAndView GetReport(){
        ModelAndView model = new ModelAndView("ReportTemplate");
        model.addObject("employeeAmount",clientService.ActiveEmployees());
        model.addObject("employeeRevDaily","AR$ "+clientService.DailySales());
        model.addObject("onlineTickets",clientService.AmountOnlineTickets());
        model.addObject("mostViewed",clientService.DayFilmMostWatched());
        model.addObject("categoryDay",clientService.CategoryDay()[0]+" AR$"+clientService.CategoryDay()[1]);
        return model;
    }
}
