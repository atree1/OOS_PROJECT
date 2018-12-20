package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ProductVO;
import org.oos.domain.StoreVO;
import org.oos.mapper.ImgurMapper;
import org.oos.service.NotifyService;
import org.oos.service.ProductService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/store/*")
public class StoreController {

	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@Setter(onMethod_= @Autowired)
    private	ImgurMapper imgurMapper;
	
	@Setter(onMethod_=@Autowired)
	private NotifyService notifyService;
	
	@GetMapping("/popup")
	public void popupGet(Long sbno, String sid,Model model) {
    	Map<String, Object> map = new HashMap<String, Object>();
	
    	map.put("sid", sid);
    	map.put("sbno", sbno);
				
		model.addAttribute("popup", notifyService.get(sbno));
	}
	
	@GetMapping("/list")
	public void storeList(Criteria cri, Long sno, Model model) {
		
		Map<String, Object> map = new HashMap<>();
		PageDTO dto = new PageDTO(cri, productService.getTotal(map));
		
		map.put("dto", dto);
		map.put("sno", sno);
		
		PageDTO pageDTO = new PageDTO(cri, productService.getTotal(map));
        
        List<Integer> pageList = new ArrayList<>();
        
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
        model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("store", storeService.get(sno));
		model.addAttribute("product", productService.getList(map));
	}
	

	@GetMapping("/detail")
	public void productRead(Long pno, Long sno, Model model) {
		
		ProductVO vo = productService.read(pno);
		
		model.addAttribute("store", storeService.get(sno));
		model.addAttribute("product", vo);
	}
	
	
	@PostMapping("/checkzzim/{sno}")
    public ResponseEntity<String> checkzzim(@PathVariable("sno") Long sno) {
		
    	String name = SecurityContextHolder.getContext().getAuthentication().getName();
	    
		if(!name.equals("anonymousUser")) {
        	StoreVO store = new StoreVO();
        	store.setMid(name);
        	store.setSno(sno);
    	 	
        	if(storeService.getStoreLike(store).size() == 0) {
        		return new ResponseEntity<String>("no",HttpStatus.OK);
    	 	}else {
    	 		return new ResponseEntity<String>("yes",HttpStatus.OK);
    	 	}
        }else {
        	return new ResponseEntity<String>(HttpStatus.OK);
        }
		
	 }
	
	@PostMapping("/autocomplete")
    @ResponseBody
    public List<String> autoComplete() {
        
        List<String> list=storeService.getName();
        return list;
    }
}
