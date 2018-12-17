package org.oos.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class StoreVO {
	private Long sno;
	private String sname;
	private String owner;
	private String sinfo;
	private String saddress;
	private String building;
	private String floor;
	private String room;
	private String tel;
	private Date regdate;
	private Date updatedate;
	private char del;
	
	private int productNum; //총 상품 개수
	private String mid; // 찜목록 가져올때 쓰려고
	
	private List<StoreImgVO> imgList;
	private List<StoreHashTagVO> hashList;

}
