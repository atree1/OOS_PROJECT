package org.oos.controller;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.oos.domain.AuthDTO;
import org.oos.domain.MemberVO;
import org.oos.persistence.MemberRepository;
import org.oos.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.java.Log;


@Controller
@RequestMapping("/oos")
@Log
public class LoginController {

	@Autowired
	PasswordEncoder pwEncoder;
	
    @Setter(onMethod_= @Autowired)
    private MemberService memberService;
	
	@Autowired
	MemberRepository repo;
	
	@GetMapping(value= {"/login","/signup","/callback"})
	public void login() {
		
	}
	
	@PostMapping(value="/snsSignup")
	public void getSns(AuthDTO dto, Model model) {
		dto.setAccess_token(dto.getAccess_token().replaceAll("\\p{Z}", "+"));
		model.addAttribute("info", dto);
	}
	
	@PostMapping(value="/idCheck/{id}")
	public ResponseEntity<String> idCheck(@PathVariable("id") String id) {
		log.info(memberService.get(id)+"");
		if(memberService.get(id) == null) {
			return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("FAIL",HttpStatus.OK);
		}
		
	}
	
	@GetMapping(value= {"/signupResult"})
	public void success() {
		
	}
	
	@PostMapping(value= {"/logout"})
	public String logout() {
		return "redirect:/main";
	}
	
	@Transactional
	@PostMapping(value= {"/with"})
	public String with(MemberVO member) {
	
		org.oos.domain.Criteria cri = new org.oos.domain.Criteria();
		cri.setAmount(100000);
		
		memberService.getList(cri).forEach(vo -> {
			
			if(member.getSns().equals("null") && vo.getMid().equals(member.getMid())) {
				memberService.remove(member.getMid());
			}else if(!member.getSns().equals("null") && vo.getMid().equals(member.getMid())) {
				memberService.remove(member.getMid());
				memberService.removeSns(member.getMid());
			}
		});
		
		return "redirect:/oos/logout";
	}
	
	@RequestMapping(value= {"/oauth"}, consumes="application/json", produces="application/json")
	public ResponseEntity<Map> oauth(@RequestBody AuthDTO dto) {
		log.info(dto+"");
		Map<String,Object> map = new HashMap<>();
		MemberVO member = memberService.get(dto.getUser_id());
		if(member == null || !member.getSns().equals(dto.getSns())) {
			map.put("state", "new");
			map.put("info", dto);
		}else {
			map.put("state", "old");
			map.put("info", memberService.getSnsAuth(dto.getUser_id()));
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	
	@Transactional
	@PostMapping(value= {"/signup"})
	public String signUpPost(@ModelAttribute("member") MemberVO vo, AuthDTO dto) {
		
		if(dto.getSns() != null) {
			dto.setUser_id(vo.getMid());
			dto.setUser_pw(vo.getMpw());
			memberService.insertSnsAuth(dto);
		}
		vo.setBirth(vo.getBirth().substring(1));
		
		String pw = pwEncoder.encode(vo.getMpw());
		vo.setMpw(pw);
		
		repo.save(vo);
		
		return "/oos/signupResult";
	}
}
