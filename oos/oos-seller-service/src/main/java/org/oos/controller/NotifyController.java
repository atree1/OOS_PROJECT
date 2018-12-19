package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.NotifyVO;
import org.oos.domain.PageDTO;
import org.oos.service.NotifyService;
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


@Controller
@Log
@RequestMapping("/notify/*")
public class NotifyController {

	@Setter(onMethod_= @Autowired)
	private NotifyService service;
	
	@Setter(onMethod_=@Autowired)
	private SellerService sellerService;
	
	@PostMapping("/sellerModify")
	public String sellerModifyPost(NotifyVO vo,Criteria cri, RedirectAttributes rttr){
		
		int result = service.modify(vo);
		
		rttr.addFlashAttribute("result", result ==1? "SUCCESS":"FAIL");
		
		return "redirect:/notify/sellerGet?sid="+vo.getSid()+"&sbno="+vo.getSbno()+"&amount="+cri.getAmount()+"&pageNum="+cri.getPageNum();
	}
	
	@GetMapping({"/sellerGet","/sellerModify"})
	public void sellerGet(Long sbno,String sid,Criteria cri,Model model) {
		
		Map<String, Object> map = new HashMap<>();
		PageDTO pageDTO = new PageDTO(cri, service.sidCount(map));
		map.put("sbno", sbno);
		map.put("sid", sid);
		map.put("dto", pageDTO);
		
		model.addAttribute("seller", sellerService.get(sid));
		model.addAttribute("notify", service.get(sbno));
		model.addAttribute("pageMaker", pageDTO);
	}
	
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
		model.addAttribute("seller", sellerService.get(sid));

	}
	
    @PostMapping("/remove")
    public String remove(Long[] sbno,String sid, RedirectAttributes rttr) {
        log.info(sbno+"");
        log.info("sid:"+sid);
        for(Long num : sbno) {
    		if(service.delete(num) == 1) {
                rttr.addFlashAttribute("result", "success");
            }
    	}
        
        return "redirect:/notify/sellerNotify?sid="+sid;
        
    }
    
    
	@GetMapping("/sellerRegister")
	public void insert(String sid,Model model) {
		
		model.addAttribute("seller", sellerService.get(sid));
    
	}
	
	@PostMapping("/sellerRegister")
	public String register(NotifyVO vo, RedirectAttributes rttr) {
		
		int result = service.insert(vo);
		
		rttr.addFlashAttribute("result", result == 1? "SUCCESS":"FAIL");
		
		return "redirect:/notify/sellerNotify?sid="+vo.getSid();
		
	} 
	
}
