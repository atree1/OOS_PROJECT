package org.oos.mapper;

import java.util.List;

import org.oos.domain.Criteria;
import org.oos.domain.MemberVO;

public interface MemberMapper {

	public List<MemberVO> getList(Criteria cri);
	
	public MemberVO get(String mid);
	
	public int insert(MemberVO vo);
	
	public int modify(MemberVO vo);
	
	public int delete(String mid);
	
	public int changeAutority(MemberVO vo);
		
	public int count(Criteria cri);
}