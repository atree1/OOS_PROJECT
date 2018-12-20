package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.oos.domain.AdminNotifyVO;

public interface AdminNotifyMapper {

public List<AdminNotifyVO> getList(Map<String, Object> map);
	
	public AdminNotifyVO get(long bno);
	
	public int delete(long bno);
	
	public int modify(AdminNotifyVO vo);
	
	public int insert(AdminNotifyVO vo);
	
	public int sidCount(Map<String, Object> map);
}
