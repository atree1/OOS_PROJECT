package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.SellerVO;
import org.oos.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class SellerServiceImpl implements SellerService {
	
	@Setter(onMethod_=@Autowired)
	private SellerMapper mapper;


	@Override
	public SellerVO get(String sid) {
		return mapper.get(sid);
	}


	@Override
	public int modify(SellerVO vo) {
		return mapper.modify(vo);
	}

	@Override
	public int remove(String sid) {
		return mapper.delete(sid);
	}


	@Override
	public int register(SellerVO vo) {
		return mapper.insert(vo);
	}


	@Override
	public List<SellerVO> getSellerList(Map<String, Object> map) {
		return mapper.getSellerList(map);
	}


	@Override
	public int getSellerCount(Map<String, Object> map) {
		return mapper.getSellerCount(map);
	}

}
