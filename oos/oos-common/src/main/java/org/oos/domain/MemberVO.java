package org.oos.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
public class MemberVO {

	private String mid;
	private String mpw;
	private String mname;
	private String pnum;
	private String birth;
	private String email;
	private String address;
	private String addressDetail;
	
	private String sns = "null";
	private char permit = 'Y';
	private char del = 'N';
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="mid")
	private List<MemberAuth> authList;
}
