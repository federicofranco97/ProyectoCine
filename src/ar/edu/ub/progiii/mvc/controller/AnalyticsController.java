package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.service.ClientService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnalyticsController {

    @Autowired
    ClientService clientService;

    @GetMapping("/analytics_graficos")
    public ModelAndView GetMainAnalytics(){
        ModelAndView model = new ModelAndView("Analytics");
        model.addObject("YearSales",clientService.YearSalesInformation("2019"));
        model.addObject("YearMovements",clientService.YearUserMovements("2019"));
        return model;
    }

    @GetMapping("/analytics_specific")
    public ModelAndView GetSpecificAnalytics(@RequestParam("year") String Year){
        ModelAndView model = new ModelAndView("Analytics");
        model.addObject("YearSales",clientService.YearSalesInformation(Year));
        model.addObject("YearMovements",clientService.YearUserMovements(Year));
        return model;
    }
    
    @GetMapping("/analytics_rateplan")
    public ModelAndView GetRatePlanning(){
        ModelAndView model = new ModelAndView("AnalyticsRatePlan");
        model.addObject("YearSales",clientService.YearSalesInformation("2019"));
        return model;
    }

}
