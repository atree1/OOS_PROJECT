package org.oos.controller;

import java.util.HashMap;
import java.util.Map;

import org.oos.service.OrderDetailService;
import org.oos.service.ReplyService;
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
	private ReplyService replyService;
	
	@Setter(onMethod_=@Autowired)
	private OrderDetailService detailService;
	
	@GetMapping("/test")
	public void test() {
		
	}
	@GetMapping("/main")
	public void storeMain(String sid, Model model) {
		log.info("register get~");
		String[] state= {"ready","shipping","complete"};
		String[] kind= {"q","r"};
		String[] range= {"day","week","month"};
		
		Long sno=storeService.getBySid(sid).getSno();
		
		Map<String, Object> map=new HashMap<>();
		
		map.put("sno", sno);
		
		
		for (String str : kind) {
			map.put("kind",str);
			model.addAttribute(str+"ReplyCnt",replyService.getNewReplyCnt(map));
				
		}
		for (String str : state) {
			map.put("state",str);
			log.info("state:"+str);
			
			model.addAttribute(str,detailService.getStateCount(map));	
		}
		
		for (String str : range) {

			map.put("range", str);		
			model.addAttribute(str,detailService.getTotal(map));	
		}
	}
	
	

}