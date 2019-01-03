package org.oos.service;

import java.util.ArrayList;
import java.util.List;

import org.oos.domain.MahoutVO;
import org.oos.domain.Mahout_MemberVO;
import org.oos.mapper.MahoutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groovy.util.logging.Log;
import groovy.util.logging.Log4j;
import lombok.Setter;

@Service
@lombok.extern.java.Log
public class MahoutServiceImpl implements MahoutService{

	@Setter(onMethod_=@Autowired)
	private MahoutMapper mapper;	
	
	@Override
	public void setTable() {
		mapper.delete();
		List<Mahout_MemberVO> memList = mapper.getOrderList();
		List<MahoutVO> userList = new ArrayList<>();
		log.info(memList+"");
		memList.forEach(vo -> {
			
			vo.getCartList().forEach(cart -> {
				MahoutVO user = new MahoutVO();
				user.setUser_id(vo.getMno());
				user.setItem_id(cart.getPno());
				user.setValue(4);
				
				userList.add(user);
			});
			 
			
			vo.getOrderList().forEach(order -> {
				int index = -1;
				MahoutVO user = new MahoutVO();
				user.setUser_id(vo.getMno());
				user.setItem_id(order.getPno());
				user.setValue(4);
				for(int i=0; i<userList.size(); i++) {
					if(userList.get(i).getItem_id().equals(order.getPno())) {
						userList.remove(i);
						user.setValue(5);
						break;
					}
				}
				userList.add(user);
			});
			
		});
		
		userList.forEach(vo -> {
			mapper.insert(vo);
		});
	}

}
