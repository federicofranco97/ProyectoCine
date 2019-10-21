package ar.edu.ub.progiii.mvc.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnalyticsController {

    @GetMapping("/analyticsGraficos")
    public ModelAndView GetMainANalytics(){
        ModelAndView model = new ModelAndView("Analytics");
        return model;
    }
}
