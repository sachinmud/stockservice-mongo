package com.sachin.stockserviceweb.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.sachin.stockserviceweb.service.UserService;
import com.sachin.userservice.model.UserModel;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
	
	@Autowired
	UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		
		UserModel user = (UserModel) service.loadUserByUsername(loggedInUser.getName());
		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
		model.setViewName("home");
		return model;
	}
}
