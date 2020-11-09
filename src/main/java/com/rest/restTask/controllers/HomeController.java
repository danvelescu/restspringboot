package com.rest.restTask.controllers;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;




@Data
@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }

    @GetMapping("/add")
    public ModelAndView addUser() {
        return new ModelAndView("adduser.html");
    }

    @GetMapping("/update")
    public ModelAndView updateUser() {
        return new ModelAndView("updateUser.html");
    }
}
