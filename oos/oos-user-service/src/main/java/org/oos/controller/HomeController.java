package org.oos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.oos.domain.Criteria;
import org.oos.domain.MemberVO;
import org.oos.domain.PageDTO;
import org.oos.service.HashTagService;
import org.oos.service.MemberService;
import org.oos.service.ProductService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class HomeController {  
	
	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@Setter(onMethod_=@Autowired)
	private HashTagService hashTagService;
	
	@Setter(onMethod_=@Autowired)
	private MemberService memberService;

	@PostMapping("/hashTag")
	@ResponseBody
	public List<String> autoComplete() {
		List<String> getName=hashTagService.getName();
		return getName;
	}
	
	@GetMapping("/test")
	public void test() {
	}
	
	@GetMapping("/")
	public String home(){
		return "redirect:/aboutus";
	}

	
	@GetMapping("/aboutus")
	public void aboutus(Model model) {
		
	}
	
	
	@GetMapping("/pay")
	public void pay(Model model){
		
	}
	
	@GetMapping(value= {"/main","/m/main","/m/shopLayout"})
	public void submain(Model model) {
		Map<String, Object> map = new HashMap<>();
		
		Criteria cri = new Criteria();
		PageDTO pageDTO = new PageDTO(cri, productService.getTotal(map));
		map.put("dto", pageDTO);
		
		model.addAttribute("bestS", storeService.getBestStore());
		model.addAttribute("bestP", productService.bestProductList());
		model.addAttribute("product", productService.getList(map));
	}
	
	@GetMapping("/oos")
	public void main() {
		
	}

}