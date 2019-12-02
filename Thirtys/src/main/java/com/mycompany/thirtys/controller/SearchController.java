package com.mycompany.thirtys.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.thirtys.service.BoardService;
import com.mycompany.thirtys.vo.BoardVO;
import com.mycompany.thirtys.vo.PageMaker;
import com.mycompany.thirtys.vo.SearchCriteria;

@Controller
@RequestMapping("/board/*")
public class SearchController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		
		logger.info(scri.toString());
		
		//model.addAttribute("list", boardService.listCriteria(scri));
		model.addAttribute("list", boardService.listSearchCriteria(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(scri);
		
		//pageMaker.setTotlaCount(boardService.listCountCriteria(scri));
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
		
		return "redirect:/board/list";
	}
	
	// 게시글 조회
	@RequestMapping(value="/readPage", method = RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		
		logger.info("readPaging GET");
		model.addAttribute("boardVO", boardService.read(bno));
		
	}
	// 게시글 수정 페이지
	@RequestMapping(value="/modifyPage", method = RequestMethod.GET)
	public void modifyPageGET(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		
		logger.info("modifyPage GET");
		model.addAttribute("boardVO", boardService.read(bno));
	}
	@RequestMapping(value="/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		
		logger.info("modifyPage POST");
		
		boardService.modify(boardVO);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("serchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		rttr.addFlashAttribute("msg", "Msuccess");
		
		return "redirect:/board/list";
	}
	
	// 게시글 삭제
	@RequestMapping(value="/deletePage", method = RequestMethod.POST)
	public String deletePage(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		
		logger.info("deletePage POST");
		
		boardService.delete(bno);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("serchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		rttr.addFlashAttribute("msg", "Dsuccess");
		
		return "redirect:/board/list";
	}
	
}
