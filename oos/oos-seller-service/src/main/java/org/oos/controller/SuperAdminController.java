package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.service.MemberService;
import org.oos.service.ProductService;
import org.oos.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/admin/*")
public class SuperAdminController {

	@Setter(onMethod_=@Autowired)
	private MemberService memberService;
	
	@Setter(onMethod_=@Autowired)
	private SellerService sellerService;	
	
	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	
	@GetMapping("/admin/manageUser")
	public void manageUser(Model model, Criteria cri) {
		Map<String, Object> map = new HashMap<>();
		
		PageDTO pageDTO = new PageDTO(cri,memberService.getUserCount(cri)); 
		
	
		map.put("dto", pageDTO);
		
		List<Integer> pageList = new ArrayList<>();
		
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("member",memberService.getUserList(map));
	}
	
	@GetMapping("/admin/manageSeller")
	public void manageSeller(Model model, Criteria cri) {
		Map<String, Object> map = new HashMap<>();
		
		PageDTO pageDTO = new PageDTO(cri,sellerService.getSellerCount(cri)); 

		map.put("dto", pageDTO);
		
		List<Integer> pageList = new ArrayList<>();
		
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("seller",sellerService.getSellerList(map));	}
	
	@GetMapping("/admin/manageProduct")
	public void manageProduct(Model model, Criteria cri) {
		Map<String, Object> map = new HashMap<>();
		
		PageDTO pageDTO = new PageDTO(cri,productService.getTotal(map)); 
	
		map.put("dto", pageDTO);
		map.put("seller", "seller");
		
		List<Integer> pageList = new ArrayList<>();
		
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("product",productService.getList(map));
	}
	
}
