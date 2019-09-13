package ar.edu.ub.progiii.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    /**
     * Método que carga al dashboard
     * @return
     */
    @GetMapping("/admin")
    public ModelAndView GetDashboard(){
        ModelAndView model = new ModelAndView("Dashboard");
        return model;
    }
}