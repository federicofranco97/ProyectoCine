package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.service.ClientService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WithdrawController {

	@Autowired
	ClientService clientService;
	/**
    Metodo que te lleva a la vista de Alivio
     */
	@GetMapping("/withdraw")
	public ModelAndView GetView(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("Withdraw");
		model.addObject("totalVirtual",clientService.GetTotalVirtual((int)request.getSession().getAttribute("EmployeeId")));
		return model;
	}

	@GetMapping("/withdrawValidate")
	public ModelAndView ValidateWithdraw(HttpServletRequest request, @RequestParam("SupId")String
			SupervisorID,@RequestParam("SupPass")String Password) {
		if(clientService.ValidateCredentials(SupervisorID,Password)){
			RedirectView redirectView = new RedirectView("/menu");
			redirectView.setExposePathVariables(false);
			return new ModelAndView(redirectView);
		}else{
			error page //q me lleve a error page
		}

	}

}
