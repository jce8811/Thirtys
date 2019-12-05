package com.mycompany.thirtys.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.thirtys.service.BoardService;
import com.mycompany.thirtys.vo.BoardVO;
import com.mycompany.thirtys.vo.PageMaker;
import com.mycompany.thirtys.vo.SearchCriteria;

@Controller
@RequestMapping("/gallery/")
public class GalleryController {
	
	private static final Logger logger = LoggerFactory.getLogger(GalleryController.class);
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		
		logger.info(scri.toString());
		model.addAttribute("list", boardService.listSearchCriteria(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(scri);
		pageMaker.setTotlaCount(boardService.listSearchCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
	}
	// 게시물 등록
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public void writeGET(BoardVO boardVO, Model model) throws Exception{
		
		logger.info("write GET");
	}
	
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String writePOST(BoardVO boardVO, RedirectAttributes rttr) throws Exception{
		
		logger.info("write POST");
		logger.info(boardVO.toString());
		
		boardService.write(boardVO);
		
		rttr.addFlashAttribute("msg", "Wsuccess");
		
		return "redirect:/gallery/list";
	}
}
