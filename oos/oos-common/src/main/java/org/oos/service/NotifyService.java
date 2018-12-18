package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.NotifyVO;

public interface NotifyService {
	
	public List<NotifyVO> getList(Map<String, Object> map);
	
	public NotifyVO get(long sbno);
	
	public int delete(long sbno);
	
	public int modify(NotifyVO vo);
	
	public int insert(NotifyVO vo);
	
	public int sidCount(Map<String, Object> map);
	
}
