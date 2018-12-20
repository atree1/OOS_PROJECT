package org.oos.controller;

import java.util.HashMap;
import java.util.Map;

import org.oos.service.OrderDetailService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class HomeController {  
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@Setter(onMethod_=@Autowired)
	private OrderDetailService detailService;
	
	@GetMapping("/test")
	public void test() {
		
	}
	@GetMapping("/main")
	public void storeMain(String sid, Model model) {
		log.info("register get~");
		Long sno=storeService.getBySid(sid).getSno();
		Map<String, Object> map=new HashMap<>();
		map.put("sno", sno);
		map.put("range", "week");
		
		model.addAttribute("ready",detailService.getStateCount(map));
		model.addAttribute("total",detailService.getTotal(map));
	}
	
	

}