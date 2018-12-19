package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.MemberVO;
import org.oos.domain.OrderDetailVO;
import org.oos.domain.OrderVO;
import org.oos.domain.PageDTO;
import org.oos.domain.StoreVO;
import org.oos.mapper.ImgurMapper;
import org.oos.persistence.MemberRepository;
import org.oos.service.CartService;
import org.oos.service.MemberService;
import org.oos.service.OrderDetailService;
import org.oos.service.OrderService;
import org.oos.service.ProductService;
import org.oos.service.ReplyService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/user/*")
public class UserController {
    @Setter(onMethod_=@Autowired)
    private CartService cartService;
    
    @Setter(onMethod_= @Autowired)
    private MemberService memberService;
    
    @Setter(onMethod_= @Autowired)
    private OrderDetailService orderDetailService;
    
    @Setter(onMethod_= @Autowired)
    private OrderService orderService;
    
    @Setter(onMethod_= @Autowired)
    private StoreService storeService;
    
    @Setter(onMethod_= @Autowired)
    private ProductService productService;
    
    @Setter(onMethod_= @Autowired)
    private	ImgurMapper imgurMapper;
    
	@Setter(onMethod_= @Autowired)
	private ReplyService replyService;

	@Autowired
	PasswordEncoder pwEncoder;
	
	@Autowired
	MemberRepository repo;
	

    @GetMapping("/mypage/reviewDetail")
	public void reviewDetail(long pno, String mid, String kind, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pno", pno);
		map.put("mid", mid);
		map.put("kind", kind);
		model.addAttribute("reviewDetail", replyService.getDetailList(map));
	}
	
	@GetMapping("/mypage/review")
	public void reviewList(Criteria cri, String mid, String kind, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cri", cri);
		map.put("mid", mid);
		map.put("kind", kind);
		
		model.addAttribute("reply", replyService.getList(map));
		PageDTO pageDTO = new PageDTO(cri,replyService.myOrderCount(map)); 
		log.info(replyService.getList(map)+"");
		List<Integer> pageList = new ArrayList<>();
	    
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }

	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
	}
	
	@GetMapping("/mypage/qnaDetail")
	public void qnaDetail(long pno, String mid, String kind, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pno", pno);
		map.put("mid", mid);
		map.put("kind", kind);
		model.addAttribute("qnaDetail", replyService.getDetailList(map));
	}
	
	@GetMapping("/myStoreList")
	public void myStore(String mid, Criteria cri, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<StoreVO> storeList = new ArrayList<>();
		 
		map.put("cri", cri);
		map.put("mid", mid);
		PageDTO pageDTO = new PageDTO(cri, memberService.getMyStoreCount(map));
		
		memberService.getMyStoreList(map).forEach(vo -> {
			storeList.add(storeService.get(vo.getSno()));
		});
		
		List<Integer> pageList = new ArrayList<>();
	    
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("storeList", storeList);
	}
	
	 @PostMapping("/myStoreList")
	    public String myStoreRemove(String mid, Long sno) {

		 	StoreVO vo = new StoreVO();
		 	vo.setMid(mid);
		 	vo.setSno(sno);
	    	storeService.delStoreLike(vo);
	    	
	    	return "redirect:/user/myStoreList?mid="+mid;
	 }
	 
	@PostMapping("/zzim/{sno}/{mid}")
    public ResponseEntity<String> insertZzim(@PathVariable("mid") String mid,@PathVariable("sno") Long sno) {
	 	StoreVO vo = new StoreVO();
	 	vo.setMid(mid);
	 	vo.setSno(sno);
	 	
	 	if(storeService.getStoreLike(vo).size() == 0) {
	 		storeService.inStoreLike(vo);
	 		return new ResponseEntity<String>("insert",HttpStatus.OK);
	 	}else {
	 		storeService.delStoreLike(vo);
	 		return new ResponseEntity<String>("delete",HttpStatus.OK);
	 	}
	 	
	 }

	
	@GetMapping("/mypage/qna")
	public void qnaList(Criteria cri, String mid, String kind, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cri", cri);
		map.put("mid", mid);
		map.put("kind", kind);
		
		model.addAttribute("reply", replyService.getList(map));
		PageDTO pageDTO = new PageDTO(cri,replyService.myOrderCount(map)); 
		
		List<Integer> pageList = new ArrayList<>();
	    
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }

	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
	}
    
    @GetMapping("/mypage/orderDetail")
    public void orderDetail(long ono, Model model) {
    	List<OrderDetailVO> list = orderDetailService.getList(ono);
        model.addAttribute("detail", list);
    }
    
    @PostMapping("/mypage/orderDetail")
    public String orderDetailRemove(long odno, long ono) {
    	orderDetailService.delete(odno);
    	
    	return "redirect:/user/mypage/orderDetail?ono="+ono;
    }
    
    @GetMapping("/list")
    public void List(Criteria cri, String mid, Model model) {
    	
    	Map<String, Object> map = new HashMap<>();
    	map.put("cri", cri);
    	PageDTO pageDTO;
    	
    	if(cri.getCategory() != null && cri.getCategory().equals("select2")) {
    		pageDTO = new PageDTO(cri, storeService.count(cri));
    	}else {
    		pageDTO = new PageDTO(cri, productService.getTotal(map));
    	}
    	
    	map.put("dto", pageDTO);
		map.put("mid", mid);
		
		List<StoreVO> store = storeService.getList(pageDTO);		
		
		if(cri.getCategory() != null && cri.getCategory().equals("select2")) {
        	model.addAttribute("store", store);
        }else{
        	model.addAttribute("product", productService.getList(map));
        }
		
		List<Integer> pageList = new ArrayList<>();
	    
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
        model.addAttribute("cri", cri);   
	    model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
        
    }
    
    @GetMapping("/mypage/orderList")
    public void orderDetailList(Criteria cri,String mid, Model model) {
        
        Map<String, Object> map = new HashMap<String, Object>();
       
        map.put("mid",mid);
        map.put("cri",cri);
        PageDTO pageDTO = new PageDTO(cri,orderService.count(map)); 
        map.put("dto", pageDTO);
   
        List<OrderVO> order = orderService.getList(map);
      
        order.forEach(vo -> {
        	List<OrderDetailVO> list  = orderDetailService.getList(vo.getOno());
        	
        	vo.setDetail(list.get(0));
        });
        
        model.addAttribute("orderList", order);
        
        List<Integer> pageList = new ArrayList<>();
        
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
        
    }
    
    @PostMapping("/mypage/orderList")
    public String remove(Long ono, String mid, RedirectAttributes rttr) {
        
        if(orderService.delete(ono) == 1) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/user/mypage/orderList?mid="+mid;
    }
    
    @GetMapping("/mypage/modify")
    public void get(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
        model.addAttribute("member",memberService.get(name));
    }
    
    @PostMapping("/modPw/{mid}/{pw}")
    public ResponseEntity<String> modifyPw(@PathVariable("mid") String mid, @PathVariable("pw") String pw) {
	 	MemberVO vo = new MemberVO();
	 	vo.setMid(mid);
	 	
	 	String password = pwEncoder.encode(vo.getMpw());
	 	vo.setMpw(password);
    	if(memberService.modifyPw(vo) == 1) {
    		return new ResponseEntity<String>("success",HttpStatus.OK);
        }
    	
	 	return new ResponseEntity<String>(HttpStatus.OK);
	 }
    
    
    @PostMapping("/mypage/modify")
    public String modify(MemberVO vo, RedirectAttributes rttr) {

        if(memberService.modify(vo) == 1){
            rttr.addFlashAttribute("modify", "success");
        }
        
        return "redirect:/user/mypage/modify";
    }
    
    @GetMapping("/cart")
    public void list(Criteria cri, String mid, Model model) {
    	
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("criteria", cri);
        map.put("mid",mid);
        
    	PageDTO pageDTO = new PageDTO(cri, cartService.count(map));
        
        List<Integer> pageList = new ArrayList<>();
        
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
        model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
        model.addAttribute("cartList",cartService.getList(map));
    }
    
}