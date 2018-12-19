package org.oos.service;

import java.util.List;

import javax.transaction.Transactional;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ProductImgVO;
import org.oos.domain.StoreVO;
import org.oos.mapper.ProductImgMapper;
import org.oos.mapper.StoreHashTagMapper;
import org.oos.mapper.StoreImgMapper;
import org.oos.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class StoreServiceImpl implements StoreService {

	@Setter(onMethod_=@Autowired)
	private StoreMapper mapper;
	
	@Setter(onMethod_=@Autowired)
	private StoreHashTagMapper hashMapper;
	
	@Setter(onMethod_=@Autowired)
	private StoreImgMapper sImgMapper;
	private ProductImgMapper pImgMapper;
	
	@Override
	public List<StoreVO> getList(PageDTO dto) {
		return mapper.getList2(dto);
	}

	@Override
	public StoreVO get(Long sno) {
//		StoreVO vo= mapper.get(sno);
//		vo.setImgList(sImgMapper.get(vo.getSno()));
//		vo.setHashList(hashMapper.getList(vo.getSno()));
		return mapper.getBySno(sno);
	}

	@Override
	@Transactional
	public int register(StoreVO vo) {
		
		mapper.insert(vo);
		vo.getImgList().forEach(img->{
			img.setSno(vo.getSno());
			sImgMapper.insert(img);
		});
		vo.getHashList().forEach(hash->{
			hash.setSno(vo.getSno());
			hashMapper.insert(hash);
		});
		return 1;
	}

	@Override
	public int remove(Long sno) {
		return mapper.delete(sno);
	}

	@Override
	@Transactional
	public int modify(StoreVO vo) {
		
		sImgMapper.deleteAll(vo.getSno());
		hashMapper.deleteAll(vo.getSno());
		if (vo.getImgList() == null) {
			return mapper.modify(vo);
		}
		
		if (vo.getImgList().size() > 0) {
			vo.getImgList().forEach(attach -> {
				attach.setSno(vo.getSno());
				sImgMapper.insert(attach);
			});
		}
		vo.getHashList().forEach(hash->{
			hash.setSno(vo.getSno());
			hashMapper.insert(hash);
		});
		return mapper.modify(vo);
	}

	@Override
	public int count(Criteria cri) {
		return mapper.count(cri);
	}

	@Override
	public List<ProductImgVO> getImg(Long sno) {
		return pImgMapper.get(sno);
	
	}
	
	@Override
    public List<String> getName() {
        return mapper.getName();
    }

	@Override
	public StoreVO getBySid(String sid) {
		// TODO Auto-generated method stub
		StoreVO vo=mapper.getBySid(sid);
		vo.setImgList(sImgMapper.get(vo.getSno()));
		vo.setHashList(hashMapper.getList(vo.getSno()));
		return vo;
	}

	@Override
	public int pNumCount(Long sno) {
		return mapper.pNumCount(sno);
	}

	@Override
	public int delStoreLike(StoreVO vo) {
		return mapper.delStoreLike(vo);
	}

	@Override
	public int inStoreLike(StoreVO vo) {
		return mapper.inStoreLike(vo);
	}

	@Override
	public List<StoreVO> getStoreLike(StoreVO vo) {
		return mapper.getStoreLike(vo);
	}

	
}
