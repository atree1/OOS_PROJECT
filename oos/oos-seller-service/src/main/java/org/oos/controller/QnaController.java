package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ReplyVO;
import org.oos.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/list")
	public String remove(Long rno, RedirectAttributes rttr) {
		
		
    		if(replyService.getRemove(rno) == 1) {
                rttr.addFlashAttribute("result", "success");
            }
    	
        
        return "redirect:/qna/list?kind=q&sno=1";
		
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
