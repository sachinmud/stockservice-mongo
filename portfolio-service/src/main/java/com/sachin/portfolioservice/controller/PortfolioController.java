package com.sachin.portfolioservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.portfolioservice.model.UserPortfolioModel;
import com.sachin.portfolioservice.model.StockPortfolioModel;
import com.sachin.portfolioservice.service.PortfolioService;

import io.swagger.annotations.Api;

@RestController()
@RequestMapping("v1/portfolio")
@Api(value = "Portfolio Service", description = "Get Portfolio Details")
public class PortfolioController {
	
	@Autowired
	PortfolioService portfolioService;
	
	@PreAuthorize("hasAuthority('portfolio:read')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserPortfolioModel> getPortfolio(@PathVariable("id") String id) {
		return new ResponseEntity<UserPortfolioModel>(portfolioService.getPortfolio(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('portfolio:read')")
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<UserPortfolioModel> getPortfolioByUser(@PathVariable("id") String id) {
		return new ResponseEntity<UserPortfolioModel>(portfolioService.getPortfoliobyUser(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('portfolio:read')")
	@GetMapping(value = "/{id}/details")
	public ResponseEntity<UserPortfolioModel> getPortfolioDetails(@PathVariable("id") String id) {
		return new ResponseEntity<UserPortfolioModel>(portfolioService.getPortfolioDetails(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('portfolio:read')")
	@GetMapping(value = "/user/{id}/details")
	public ResponseEntity<UserPortfolioModel> getPortfolioDetailsByUser(@PathVariable("id") String id) {
		return new ResponseEntity<UserPortfolioModel>(portfolioService.getPortfolioDetailsbyUser(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('portfolio:write')")
	@PostMapping(value = "/")
	public ResponseEntity<UserPortfolioModel> savePortfolio(@RequestBody UserPortfolioModel portfolioVO) {
		return new ResponseEntity<UserPortfolioModel>(portfolioService.savePortfolio(portfolioVO), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('portfolio:write')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserPortfolioModel> savePortfolio(@PathVariable("id") String id, @RequestBody UserPortfolioModel portfolioVO) {
		return new ResponseEntity<UserPortfolioModel>(portfolioService.savePortfolio(portfolioVO), HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasAuthority('portfolio:write')")
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePortfolio(@PathVariable("id") String id) {
		portfolioService.deletePortfolio(id);
	}
	
}
