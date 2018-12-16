package org.oos.mapper;

import java.util.List;

import org.oos.domain.StoreImgVO;

public interface StoreImgMapper {
	
	public List<StoreImgVO> get(Long sno);
	
	public int insert(StoreImgVO vo);
	
	public int delete(String uuid);
}
