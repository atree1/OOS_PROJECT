package org.oos.controller;
import java.util.List;

import org.oos.domain.StoreImgVO;
import org.oos.domain.StoreVO;
import org.oos.service.HashTagService;
import org.oos.service.SellerService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/store/*")
public class StoreController {
	
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;

	@Setter(onMethod_=@Autowired)
	private SellerService sellerService;
	
	@Setter(onMethod_=@Autowired)
	private HashTagService hashTagService;
	
	@GetMapping("/register")
	public void storeRegister(String sid, Model model) {
		log.info("register get~");
		model.addAttribute("owner",sid );
		model.addAttribute("hashTagList",hashTagService.getList());
	}
	
	@PostMapping("/register")
	public String storeRegisterPost(StoreVO vo) {
		log.info(""+vo);
		storeService.register(vo);
		return "redirect:/exam/home";
	}
	@GetMapping("/read")
	public void storeRead(Long sno, Model model) {
		log.info("register get~");
		StoreVO vo=storeService.get(sno);
		model.addAttribute("store",vo);
	}
	@GetMapping("/modify")
	public void storeModify(Long sno, Model model) {
		log.info("modify get~");
		StoreVO vo=storeService.get(sno);
		model.addAttribute("store",vo);
		model.addAttribute("hashTagList",hashTagService.getList());
	}
	@PostMapping("/modify")
	public String storeModifyPost(StoreVO vo) {
		log.info(""+vo);
		storeService.modify(vo);
		return "redirect:/exam/home";
	}
	
	

}