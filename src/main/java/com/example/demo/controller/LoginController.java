package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.User;
import com.example.demo.services.UserServiceImpl;

@Controller
public class LoginController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping(value={"/login","/"})
    public String  loginView(){
        return "login";
    }
	
	@GetMapping("/register")
    public String  registerView(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
    public String createAccount(@ModelAttribute User user, Model model){
       System.out.println(user.toString());
        userService.save(user);
        model.addAttribute("regSucc","You have been registred successfully");
        return "login";
    }


}
