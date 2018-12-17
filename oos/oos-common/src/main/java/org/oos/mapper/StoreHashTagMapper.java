package org.oos.mapper;

import java.util.List;

import org.oos.domain.StoreHashTagVO;

public interface StoreHashTagMapper {
	
	public List<StoreHashTagVO> getList(Long sno);
	public void insert(StoreHashTagVO vo);
	public void deleteAll(Long sno);
}
