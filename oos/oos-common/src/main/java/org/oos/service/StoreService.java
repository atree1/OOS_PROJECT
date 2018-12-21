package org.oos.service;

import java.util.List;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ProductImgVO;
import org.oos.domain.StoreVO;

public interface StoreService {
	
	public List<StoreVO> getList(PageDTO dto);
	
	public List<StoreVO> getStoreList(PageDTO dto);
	
	public List<String> getName();
	
	public List<ProductImgVO> getImg(Long sno);
	
	public StoreVO get(Long sno);
	
	public StoreVO getBySid(String sid);
	
	public int register(StoreVO vo);
	
	public int remove(Long sno);
	
	public int upVisitCnt(Long sno);
	
	public int modify(StoreVO vo);
	
	public int count(Criteria cri);	
	
	public int pNumCount(Long sno);
	
	public int delStoreLike(StoreVO vo);
	
	public int inStoreLike(StoreVO vo);
	
	public List<StoreVO> getStoreLike(StoreVO vo);
	
	
}
