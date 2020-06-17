package br.com.marketlist.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1")
public class HomeController extends AbstractController{
	
	
	@GetMapping
	public String home(){
		return "OK";
	}
	
}
