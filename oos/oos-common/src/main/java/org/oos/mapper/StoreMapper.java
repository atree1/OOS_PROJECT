package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.StoreVO;

public interface StoreMapper {
	/*
	public List<StoreVO> getList(PageDTO dto);*/
	
	public List<StoreVO> getList2(PageDTO dto);
	
	public List<String> getName();
	
	public List<StoreVO> getStoreList(PageDTO dto);
	
	public StoreVO get(Long sno);
	
	public StoreVO getBySno(Long sno);
	
	public StoreVO getBySid(String sid);
	
	public int insert(StoreVO vo);
	
	public int modify(StoreVO vo);
	
	public int delete(Long sno);
	
	public int count(Criteria cri);
	
	public int pNumCount(Long sno);
	
	public int delStoreLike(StoreVO vo);
	
	public int inStoreLike(StoreVO vo);
	
	public List<StoreVO> getStoreLike(StoreVO vo);

}
