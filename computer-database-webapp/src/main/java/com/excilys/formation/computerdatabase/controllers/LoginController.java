package com.excilys.formation.computerdatabase.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.computerdatabase.controllers.constants.Views;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory
            .getLogger(LoginController.class);

    public LoginController() {
        // TODO Auto-generated constructor stub
    }

    @GetMapping(value = "/login")
    protected ModelAndView showLoginForm() {
        ModelAndView mav = new ModelAndView(Views.LOGIN);
        return mav;
    }
}
