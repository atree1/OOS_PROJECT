package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.SellerVO;

public interface SellerMapper {

	public List<SellerVO> getSellerList(Map<String, Object> map);
	
	public int getSellerCount(Map<String, Object> map);
	
	public SellerVO get(String sid);
	
	public int insert(SellerVO vo);
	
	public int modify(SellerVO vo);
	
	public int delete(String sid);
	
	public int authorize(String sid);
	
}
