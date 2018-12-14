package org.oos.mapper;

import org.oos.domain.ProductImgVO;
import org.oos.domain.StoreImgVO;

public interface StoreImgMapper {
	
	public ProductImgVO get(Long sno);
	
	public int insert(StoreImgVO vo);
	
	public int delete(String uuid);
}
