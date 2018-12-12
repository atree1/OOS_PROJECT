package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.oos.domain.CartVO;
import org.oos.domain.Criteria;

public interface CartMapper {

	public int insert(CartVO vo);
	public int delete(Long cno);
	public List<CartVO> getList(Map<String, Object> map);
	public CartVO get(Long cno);
	public int modify(CartVO vo);
	public int count(Map<String, Object> map);
	
}
