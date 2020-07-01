package com.sachin.stockserviceweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sachin.stockserviceweb.service.UserService;
import com.sachin.userservice.model.UserModel;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		
		List<UserModel> users = service.getAllUsers();
		ModelAndView model = new ModelAndView();
		model.addObject("users", users);
		model.setViewName("user/search");
		return model;
	}	

}
