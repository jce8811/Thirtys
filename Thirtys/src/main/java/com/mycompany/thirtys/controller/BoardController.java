package com.mycompany.thirtys.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.thirtys.service.BoardService;
import com.mycompany.thirtys.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService boardService;

	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		
		logger.info("list");
		model.addAttribute("list", boardService.list());
	}
	
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
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public String read(@RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("read GET");
		model.addAttribute("boardVO", boardService.read(bno));
		
		return "board/read";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("modify GET");
		model.addAttribute("boardVO", boardService.read(bno));
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO boardVO, RedirectAttributes rttr) throws Exception {
		
		logger.info("modify POST");
		
		boardService.modify(boardVO);
		rttr.addFlashAttribute("msg", "Msuccess");
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		
		logger.info("delete POST");
		
		boardService.delete(bno);
		rttr.addFlashAttribute("msg", "Dsuccess");
		
		return "redirect:/board/list";
	}
}
