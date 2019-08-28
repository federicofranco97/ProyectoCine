package ar.edu.ub.progiii.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

    @GetMapping("/report_daily")
    public ModelAndView GetReport(){
        ModelAndView model = new ModelAndView("ReportTemplate");
        return model;
    }
}
