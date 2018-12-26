package org.oos.mapper;

import java.util.List;

import org.oos.domain.HashTagVO;



public interface HashTagMapper {
	
	public List<String> getName();
	
	public int get(Long hno);
	
	public List<HashTagVO> getList();
	
	public int insert(String tag);
	
	public int delete(int hno);
}
