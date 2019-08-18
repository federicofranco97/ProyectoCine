package ar.edu.ub.progiii.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    @GetMapping("/Admin")
    public ModelAndView GetDashboard(){
        ModelAndView model = new ModelAndView("Dashboard");
        return model;
    }
}