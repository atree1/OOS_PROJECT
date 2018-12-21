package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ReplyVO;
import org.oos.domain.StoreVO;
import org.oos.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Setter(onMethod_=@Autowired)
	private ReplyService replyService;
	
	//화면리스트
	@GetMapping("/list")
	public void getList(Model model,String kind, Criteria cri, int sno) {
		
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("sno", sno);
		PageDTO pageDTO = new PageDTO(cri, replyService.count(map));
		map.put("dto", pageDTO);
		
		List<ReplyVO> reply = replyService.getStoreReply(map);
		
		model.addAttribute("replyList", reply);
		log.info(reply+"");
		List<Integer> pageList = new ArrayList<>();

		for (int i = pageDTO.getStartPage(); i <= pageDTO.getEndPage(); i++) {
			pageList.add(i);
		}

		model.addAttribute("pageList", pageList);
		model.addAttribute("pageMaker", pageDTO);
		
	}
	
	//리스트 1단 삭제
	@PostMapping("/list")
	public String remove(Long rno, RedirectAttributes rttr) {
		
		
    		if(replyService.getRemove(rno) == 1) {
                rttr.addFlashAttribute("result", "success");
            }
    	
        
        return "redirect:/qna/list?kind=q&sno=1";
		
	}
	
	//팝업창화면
	@GetMapping("/qnaDetail")
	public void qnaDetail(long pno, String kind, int parent, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
  
		
		map.put("pno", pno);
		map.put("kind", kind);
		map.put("parent", parent);
		model.addAttribute("replyList", replyService.sellerReply(map));
}
	//팝업창화면 댓글등록하기
	@PostMapping("/qnaDetail")
	public String popInsert(ReplyVO vo) {
		int count = replyService.sellerInsert(vo);
		
    	return "redirect:/qna/qnaDetail?kind=q&pno="+vo.getPno()+"&parent="+vo.getParent();
	}
}

/*Map<String, Object> map = new HashMap<String, Object>();

map.put("sid", sid);
map.put("cri", cri);
PageDTO pageDTO = new PageDTO(cri, service.sidCount(map));
map.put("dto", pageDTO);

List<NotifyVO> notify = service.getList(map);
		
model.addAttribute("notify", notify);

List<Integer> pageList = new ArrayList<>();

for (int i = pageDTO.getStartPage(); i <= pageDTO.getEndPage(); i++) {
	pageList.add(i);
}

model.addAttribute("pageList", pageList);
model.addAttribute("pageMaker", pageDTO);
*/
