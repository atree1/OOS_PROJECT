package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.AdminNotifyVO;
import org.oos.mapper.AdminNotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class AdminNotifyServiceImpl implements AdminNotifyService {

	@Setter(onMethod_=@Autowired)
	private AdminNotifyMapper mapper;
	
	@Override
	public List<AdminNotifyVO> getList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.getList(map);
	}

	@Override
	public AdminNotifyVO get(Long bno) {
		// TODO Auto-generated method stub
		return mapper.get(bno);
	}

	@Override
	public int delete(Long bno) {
		// TODO Auto-generated method stub
		return mapper.delete(bno);
	}

	@Override
	public int modify(AdminNotifyVO vo) {
		// TODO Auto-generated method stub
		return mapper.modify(vo);
	}

	@Override
	public int insert(AdminNotifyVO vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int sidCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.sidCount(map);
	}

}
