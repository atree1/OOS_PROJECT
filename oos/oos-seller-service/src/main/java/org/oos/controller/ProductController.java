package org.oos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.CategoryVO;
import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ProductOptionVO;
import org.oos.domain.ProductVO;
import org.oos.mapper.ImgurMapper;
import org.oos.service.AutoMlService;
import org.oos.service.ProductService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Setter(onMethod_ = @Autowired)
	private ProductService productService;

	@Setter(onMethod_ = @Autowired)
	private StoreService storeService;
	@Setter(onMethod_ = @Autowired)
	private AutoMlService autoMIService;
	@Setter(onMethod_=@Autowired)
	private ImgurMapper imgurMapper;
	
	@GetMapping("/read")
	public void productGet(Long pno,Long sno, Model model) {

		ProductVO vo = productService.read(pno);
		model.addAttribute("img", imgurMapper.getList());
		model.addAttribute("store", storeService.get(sno));
		model.addAttribute("product", vo);
	}
	
	@GetMapping("/list")
	public void productList(Criteria cri,Long sno, Model model) {
		
		Map<String, Object> map = new HashMap<>();
		PageDTO dto = new PageDTO(cri, productService.getTotal(map));
		
		map.put("dto", dto);
		map.put("sno", sno);
		
		PageDTO pageDTO = new PageDTO(cri, productService.getTotal(map));
        
        List<Integer> pageList = new ArrayList<>();
        
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
        model.addAttribute("img", imgurMapper.getList());
        model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("store", storeService.get(sno));
		model.addAttribute("product", productService.getList(map));
	}

	@GetMapping("/register")
	public void productRegister(Long sno, Model model) {
		model.addAttribute("store", storeService.get(sno));
	}

	@PostMapping("/register")
	public String productRegisterPost(ProductVO vo, RedirectAttributes rttr, Long sno) {
		List<CategoryVO> cateList = new ArrayList<>();
		log.info(""+vo);
		String firstPath = "C:\\upload\\" + vo.getImgList().get(0).getIpath() + "\\" + vo.getImgList().get(0).getUuid() + "_" + vo.getImgList().get(0).getIname();
		try {
			List<String> list = autoMIService.predict("oos-atree-224402", "us-central1", "ICN8521692284338889379",
					firstPath, "0.7");

			list.forEach(name -> {
				CategoryVO catevo = new CategoryVO();
				log.info("category:" + name);
				catevo.setCatename(name);
				log.info("catevo" + catevo);
				log.info(""+cateList.add(catevo));
			});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vo.getImgList().forEach(img -> {
			String path = "C:\\upload\\" + img.getIpath() + "\\" + img.getUuid() + "_" + img.getIname();
			
		});
		vo.setCateList(cateList);

		int result = productService.register(vo);

		rttr.addFlashAttribute("result", result == 1 ? "SUCCESS" : "FAIL");

		return "redirect:/product/list?sno="+storeService.get(sno).getSno();
	}
	
	@PostMapping("/autocomplete")
	@ResponseBody
	public List<String> autoComplete() {

		List<String> list = productService.getName();
		log.info("" + list);
		return list;
	}

}