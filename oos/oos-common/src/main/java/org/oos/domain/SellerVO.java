package org.oos.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data
@Table(name="tbl_seller")
public class SellerVO {

	@Id
	private String sid;
	private String spw;
	private String sname;
	private String bnum;
	private String pnum;
	private String tel;
<<<<<<< HEAD
	private Date regdate= new Date();
	private Date updatedate= new Date();
	private  char del= 'N';
=======
	private Date regdate;
	private Date updatedate;
	private  char del;
	private char permit;
>>>>>>> branch 'master' of https://github.com/atree1/OOS_PROJECT.git
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="sid")
	private List<SellerAuthVO> authList;
}
