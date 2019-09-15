package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class ReportController {

    @Autowired
    ClientService clientService;
    /**
    Metodo que te lleva a la vista de reportes de ventas del dia
     */
    @GetMapping("/ventas_dia")
    public ModelAndView GetReport(){
        ModelAndView model = new ModelAndView("ReportTemplate");
        if(clientService.ActiveEmployees().equals("0") || clientService.ActiveEmployees()==null
                || clientService.ActiveEmployees().equals("")){
            ModelAndView errorModel = new ModelAndView("ErrorPage");
            errorModel.addObject("Contenido", Arrays.asList("Error","No hay actividad registrada para el este dia,por" +
                    " favor dirigas cree un ticket si considera que el resultado obtenido es erroneo."));
            return errorModel;
        }
        model.addObject("employeeAmount",clientService.ActiveEmployees());
        model.addObject("employeeRevDaily","AR$ "+clientService.DailySales());
        model.addObject("onlineTickets",clientService.AmountOnlineTickets());
        model.addObject("mostViewed",clientService.DayFilmMostWatched());
        model.addObject("categoryDay",clientService.CategoryDay()[0]+" AR$"+clientService.CategoryDay()[1]);
        model.addObject("supervisorsActive",clientService.SupervisorsActiveDay());
        return model;
    }
}
