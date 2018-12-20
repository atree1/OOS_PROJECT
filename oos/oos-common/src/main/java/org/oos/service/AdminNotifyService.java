package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.AdminNotifyVO;

public interface AdminNotifyService {

public List<AdminNotifyVO> getList(Map<String, Object> map);
	
	public AdminNotifyVO get(Long bno);
	
	public int delete(Long bno);
	
	public int modify(AdminNotifyVO vo);
	
	public int insert(AdminNotifyVO vo);
	
	public int sidCount(Map<String, Object> map);
	
}
