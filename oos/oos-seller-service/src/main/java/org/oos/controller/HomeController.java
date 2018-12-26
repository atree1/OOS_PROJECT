package org.oos.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.oos.domain.SellerVO;
import org.oos.domain.StoreVO;
import org.oos.service.OrderDetailService;
import org.oos.service.ReplyService;
import org.oos.service.SellerService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class HomeController {

	@Setter(onMethod_ = @Autowired)
	private StoreService storeService;
	@Setter(onMethod_ = @Autowired)
	private ReplyService replyService;

	@Setter(onMethod_ = @Autowired)
	private SellerService sellerService;

	@Setter(onMethod_ = @Autowired)
	private OrderDetailService detailService;

	@GetMapping("/test")
	public void test() {

	}

	@GetMapping("/main")
	@PreAuthorize("isAuthenticated()")
	public String storeMain(Principal principal, Model model) {
		log.info("register get~");
		String[] state = { "ready", "shipping", "complete" };
		String[] kind = { "q", "r" };
		String[] range = { "day", "week", "month" };
		String sid=principal.getName();
		
		if (sellerService.get(sid).getSno() == 0) {
			return "redirect:/store/register";
		}
		StoreVO vo = storeService.getBySid(sid);
		
		log.info(""+vo);
		
		
		Long sno=vo.getSno();
		int visitcnt = vo.getVisitcnt();
		

		Map<String, Object> map = new HashMap<>();

		map.put("sno", sno);

		for (String str : kind) {
			map.put("kind", str);
			model.addAttribute(str + "ReplyCnt", replyService.getNewReplyCnt(map));

		}
		for (String str : state) {
			map.put("state", str);
			log.info("state:" + str);

			model.addAttribute(str, detailService.getStateCount(map));
		}

		for (String str : range) {

			map.put("range", str);
			model.addAttribute(str, detailService.getTotal(map));
		}

		model.addAttribute("visitcnt", visitcnt);
		return "/main";

	}

}