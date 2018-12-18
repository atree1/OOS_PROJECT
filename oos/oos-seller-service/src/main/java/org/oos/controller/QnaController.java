package org.oos.controller;

import java.util.HashMap;
import java.util.Map;

import org.oos.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Setter(onMethod_=@Autowired)
	private ReplyService replyService;
	
	@GetMapping("/list")
	public void getList(Long pno, String kind, String mid, Model model) {
		
		Map<String,Object> map  = new HashMap<>();
		map.put("pno", pno);
		map.put("kind", kind);
		map.put("mid", mid);
		
		model.addAttribute("replyList", replyService.getDetailList(map));
		
	}
}
