package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Arrays;

@Controller
public class ReportController {

    @Autowired
    ClientService clientService;

    @GetMapping("/reporte_dia")
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
        model.addObject("serverDate",clientService.GetServerDate());
        return model;
    }

    @GetMapping("/reporte_mes")
    public ModelAndView GetMonthlyReport() throws ParseException {
        ModelAndView model = new ModelAndView("ReportTemplate");
        if(clientService.ActiveEmployees().equals("0") || clientService.ActiveEmployees()==null
                || clientService.ActiveEmployees().equals("")){
            ModelAndView errorModel = new ModelAndView("ErrorPage");
            errorModel.addObject("Contenido", Arrays.asList("Error","No hay actividad registrada para el este dia,por" +
                    " favor dirigas cree un ticket si considera que el resultado obtenido es erroneo."));
            return errorModel;
        }
        model.addObject("employeeAmount",clientService.GetEmployeesActiveMonth());
        model.addObject("employeeRevDaily","AR$ "+clientService.GetMonthlySales());
        model.addObject("onlineTickets",clientService.GetOnlineBookingsMonth());
        model.addObject("mostViewed",clientService.MonthFilmMostWatched());
        model.addObject("categoryDay",clientService.CategoryMonth()[0]+" AR$"+clientService.CategoryMonth()[1]);
        model.addObject("supervisorsActive",clientService.GetSupervisorsOnlineMonth());
        model.addObject("serverDate",clientService.GetServerMonth());
        return model;
    }
}
