package com.sachin.stockserviceweb.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController {

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam Map<String, String> reqParam) {
		
		return "/permission/search";
	}
}
