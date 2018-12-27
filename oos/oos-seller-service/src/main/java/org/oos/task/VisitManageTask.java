package org.oos.task;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.oos.service.OrderDetailService;
import org.oos.service.ProductService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@Component
public class VisitManageTask  {


	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@Setter(onMethod_ = @Autowired)
	private OrderDetailService detailService;

	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	@Scheduled(cron="0 0 0 * * *")
	@Transactional
	public void resetVisit() throws Exception{
		log.info("visit reset");
//<<<<<<< HEAD
//		Map<String, Object> map = new HashMap<>();
//		  
//		map.put("range", "day");
//		int dayIncome = detailService.getTotal(map);
//=======
//>>>>>>> branch 'master' of https://github.com/atree1/OOS_PROJECT.git
		
		storeService.resetVisitCnt();
		productService.resetVisitCnt();
	}
	
}
