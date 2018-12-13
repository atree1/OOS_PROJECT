package org.oos.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam/*")
public class AdminController {
	
	@GetMapping({"comment", "edit", "home", "manage", 
		"paysettle", "products", "seller"})
	public void admin() {
		
	}
	
}
