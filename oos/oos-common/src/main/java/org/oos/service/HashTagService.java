package org.oos.service;

import java.util.List;

import org.oos.domain.HashTagVO;

public interface HashTagService {
	
	public List<String> getName();

	public List<HashTagVO> getList();
}
