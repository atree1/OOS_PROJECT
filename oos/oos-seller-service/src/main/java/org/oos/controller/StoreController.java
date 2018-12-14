package org.oos.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.oos.domain.ProductOptionVO;
import org.oos.domain.ProductVO;
import org.oos.service.AutoMlService;
import org.oos.service.ProductService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/store/*")
public class StoreController {
	
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@GetMapping("/register")
	public void productRegister(Long sno, Model model) {
		model.addAttribute("store", storeService.get(sno));
	}
	
	@PostMapping("/register")
	public String productRegisterPost(ProductVO vo,String[] size, Long[] qty,RedirectAttributes rttr, Long sno) {
		return null;
	}
	
}