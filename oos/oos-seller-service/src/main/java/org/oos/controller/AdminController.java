package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.OrderDetailVO;
import org.oos.domain.OrderVO;
import org.oos.domain.PageDTO;
import org.oos.service.OrderDetailService;
import org.oos.service.OrderService;
import org.oos.service.ProductService;
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
@RequestMapping("/exam/*")
public class AdminController {

	@Setter(onMethod_ = @Autowired)
	private OrderService orderService;

	@Setter(onMethod_ = @Autowired)
	private OrderDetailService orderDetailService;

	@Setter(onMethod_ = @Autowired)
	private ProductService productService;

	@GetMapping({ "comment", "edit", "home", "paysettle", "products", "seller" })
	public void admin() {

	}

	@GetMapping("/manage")
	public void orderList(Model model, long sno, Criteria cri) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("sno", sno);
		map.put("cri", cri);
		PageDTO pageDTO = new PageDTO(cri, orderDetailService.snoCount(sno));
		map.put("dto", pageDTO);
		
		List<OrderDetailVO> order = orderDetailService.joinList(map);
		model.addAttribute("orderList", order);
		
		List<Integer> pageList = new ArrayList<>();

		for (int i = pageDTO.getStartPage(); i <= pageDTO.getEndPage(); i++) {
			pageList.add(i);
		}
		
		model.addAttribute("pageList", pageList);
		model.addAttribute("pageMaker", pageDTO);

	}
	
    @PostMapping("/manage")
    public String remove(Long[] odno, RedirectAttributes rttr) {
        log.info(odno+"");
    	for(Long num : odno) {
    		if(orderDetailService.delete(num) == 1) {
                rttr.addFlashAttribute("result", "success");
            }
    	}
        
        return "redirect:/exam/manage?sno=1";
    }
    
    @PostMapping("/modify")
    public String modify(String[] option, RedirectAttributes rttr) {
        
        
    	for(String num : option) {    		
    		String[] list= num.split("_");
    		Long odno = Long.parseLong(list[0]);
    		Long dno = Long.parseLong(list[1]);
    		String state = list[2];
    		        
    		if(state.equals("ready")) {
    			state = "준비중";
    		}else if(state.equals("shipping")){
    			state = "배송중";
    		}else if(state.equals("complete")) {
    			state = "배송완료";
    		}
    		
    		
    		OrderDetailVO vo = new OrderDetailVO();
    		vo.setOdno(odno);
    		vo.setDetail_state(state);
    		vo.setDno(dno);
    		log.info(vo+"");
    		orderDetailService.modify(vo);
    		
    	}
    	
       return "redirect:/exam/manage?sno=1";
    }
}
/*    @GetMapping("/manage")
    public String modify(OrderVO vo, RedirectAttributes rttr) {
    	
    	if(orderService.modify(vo) == 1) {
    		rttr.addFlashAttribute("result", "success");
    	}
    	return  "redirect:/exam/manage?sno=1";
    }
}*/
