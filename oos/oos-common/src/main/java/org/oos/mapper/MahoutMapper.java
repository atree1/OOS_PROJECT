package org.oos.mapper;

import java.util.List;

import org.oos.domain.CategoryVO;
import org.oos.domain.MahoutVO;
import org.oos.domain.Mahout_MemberVO;

public interface MahoutMapper {

	public List<Mahout_MemberVO> getOrderList();
	
	public int insert(MahoutVO vo);

	public int delete();
	
	public int insert(CategoryVO vo);
	
}
