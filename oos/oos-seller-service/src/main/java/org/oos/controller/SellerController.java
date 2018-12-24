package org.oos.controller;

import org.oos.domain.SellerVO;
import org.oos.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/seller/*")
public class SellerController {

	@Setter(onMethod_=@Autowired)
	private SellerService sellerService;
	
	@GetMapping("/login")
	public void login() {
		
	};
	
	@GetMapping("/signUp")
	public void getRegister(Model model) {
		
		
		
	}
	
	@PostMapping("/signUp")
	public String postRegister(RedirectAttributes rttr, SellerVO vo) {
		
		int result = sellerService.register(vo);
		
		rttr.addFlashAttribute("result", result == 1? "SUCCESS":"FAIL");
		
		return "redirect:/exam/home";
	}
}
