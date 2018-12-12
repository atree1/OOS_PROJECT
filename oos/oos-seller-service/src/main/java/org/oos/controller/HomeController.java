package org.oos.controller;

import java.util.HashMap;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class HomeController {  
	

	@GetMapping("/test")
	public void test() {
		
	}
	
	

}