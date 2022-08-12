package ru.kata.spring.boot_security.demo.controller;

import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class HomeController {

    private UserService userService;

    @Autowired
    private void setUserService(UserService us) {
        this.userService = us;
    }

    @RequestMapping("/")
    public String getIndex(){
        return "redirect:/login";
    }
}
