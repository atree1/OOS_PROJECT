package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.NotifyVO;
import org.oos.domain.OrderDetailVO;
import org.oos.domain.PageDTO;
import org.oos.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;


@Controller
@Log
@RequestMapping("/notify/*")
public class NotifyController {

	@Setter(onMethod_= @Autowired)
	private NotifyService service;
	
	
	@GetMapping("/sellerNotify")
	public void getList(Model model, String sid, Criteria cri) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("sid", sid);
		map.put("cri", cri);
		PageDTO pageDTO = new PageDTO(cri, service.sidCount(map));
		map.put("dto", pageDTO);
		
		List<NotifyVO> notify = service.getList(map);
				
		model.addAttribute("notify", notify);
		
		List<Integer> pageList = new ArrayList<>();

		for (int i = pageDTO.getStartPage(); i <= pageDTO.getEndPage(); i++) {
			pageList.add(i);
		}
		
		model.addAttribute("pageList", pageList);
		model.addAttribute("pageMaker", pageDTO);

	}
	
	
	
	@GetMapping("/sellerRegister")
	public void insert() {
		
		
	}
	
	@PostMapping("/register")
	public String register(NotifyVO vo, RedirectAttributes rttr) {
		
		log.info(""+ vo);
		service.insert(vo);
		rttr.addFlashAttribute("result", vo.getSbno());
		
		return "redirect:/notify/sellerNotify";
		
	} 
	
}
