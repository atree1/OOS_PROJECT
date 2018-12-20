package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.CartVO;
import org.oos.domain.Criteria;
import org.oos.domain.OrderDetailVO;
import org.oos.domain.ProductVO;
import org.oos.service.CartService;
import org.oos.service.MemberService;
import org.oos.service.OrderDetailService;
import org.oos.service.OrderService;
import org.oos.service.ProductService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/order/*")
public class OrderController {
	
	@Setter(onMethod_=@Autowired)
	private OrderService orderService;
	
	@Setter(onMethod_=@Autowired)
	private CartService cartService;
	
	@Setter(onMethod_=@Autowired)
	private OrderDetailService orderDetailService;
	
	@Setter(onMethod_=@Autowired)
	private MemberService memberService;

	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	@GetMapping("/success")
	public void successGET(Long ono, Model model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mid",name);
		
		List<CartVO> cartList = cartService.getList(map);
		List<OrderDetailVO> list = orderDetailService.getListByOno(ono);
		list.forEach(vo -> {
			
			cartList.forEach(cart -> {
				if(cart.getOpno() == vo.getOpno()) {
					cartService.remove(cart.getCno());
				}
			});
			
		});
		log.info(list+"");
		model.addAttribute("order", list);
	}
	
	@GetMapping("/list")
    public void orderListGET(String[] info, Model model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		
        List<OrderDetailVO> list=new ArrayList<>();

        for (String size : info) {
        	String[] sizeInfo=size.split("_");
            
            OrderDetailVO vo=new OrderDetailVO();
            Long opno = Long.parseLong(sizeInfo[1]);
            
            vo.setOpno(opno);
            ProductVO pVO=productService.read(Long.parseLong(sizeInfo[0]));
            
            pVO.getOptList().forEach(opt->{
                if(opt.getOpno().equals(opno)) {  

                    vo.setOption(opt);
                }
            });
            
            vo.setPno(Long.parseLong(sizeInfo[0]));
            vo.setProduct(pVO);
            vo.setQty(Long.parseLong(sizeInfo[2]));
            vo.setMid(name);
            vo.setSno(Long.parseLong(sizeInfo[3]));
            list.add(vo);
        }

       
        model.addAttribute("orderList",list);
    }
	
	

}
