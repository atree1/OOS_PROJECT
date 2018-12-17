package org.oos.service;

import java.util.List;

import org.oos.domain.HashTagVO;
import org.oos.mapper.HashTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;

@Service
public class HashTagServiceImpl implements HashTagService {

	@Setter(onMethod_=@Autowired)
	private HashTagMapper mapper;
	
	@Override
	public List<String> getName() {
		// TODO Auto-generated method stub
		return mapper.getName();
	}

	@Override
	public List<HashTagVO> getList() {
		// TODO Auto-generated method stub
		return mapper.getList();
	}

}
