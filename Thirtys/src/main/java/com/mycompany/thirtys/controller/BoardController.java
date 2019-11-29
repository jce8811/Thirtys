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
import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.PageMaker;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService boardService;

	/*
	 * @RequestMapping(value="/list", method = RequestMethod.GET) public void
	 * list(Model model) throws Exception {
	 * 
	 * logger.info("list"); model.addAttribute("list", boardService.list()); }
	 */
	
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
		
		return "redirect:/board/listPage";
	}
	
	/*
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public String read(@RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("read GET");
		model.addAttribute("boardVO", boardService.read(bno));
		
		return "board/read";
	}
	*/
	
	@RequestMapping(value="/readPage", method = RequestMethod.GET)
	public String readPage(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		logger.info("readPaging GET");
		model.addAttribute("boardVO", boardService.read(bno));
		
		return "board/readPage";
	}
	
	/*
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("modify GET");
		model.addAttribute("boardVO", boardService.read(bno));
	}
	*/
	@RequestMapping(value="/modifyPage", method = RequestMethod.GET)
	public void modifyPageGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		logger.info("modifyPage GET");
		model.addAttribute("boardVO", boardService.read(bno));
	}
	/*
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO boardVO, RedirectAttributes rttr) throws Exception {
		
		logger.info("modify POST");
		
		boardService.modify(boardVO);
		rttr.addFlashAttribute("msg", "Msuccess");
		
		return "redirect:/board/list";
	}
	*/
	@RequestMapping(value="/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO boardVO, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) throws Exception {
		
		logger.info("modifyPage POST");
		
		boardService.modify(boardVO);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "Msuccess");
		
		return "redirect:/board/listPage";
	}
	
	/*
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		
		logger.info("delete POST");
		
		boardService.delete(bno);
		rttr.addFlashAttribute("msg", "Dsuccess");
		
		return "redirect:/board/list";
	}
	*/
	@RequestMapping(value="/deletePage", method = RequestMethod.POST)
	public String deletePage(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) throws Exception {
		
		logger.info("deletePage POST");
		
		boardService.delete(bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "Dsuccess");
		
		return "redirect:/board/listPage";
	}
	
	/*
	 * @RequestMapping(value="/listCri", method = RequestMethod.GET) public void
	 * list(Criteria cri, Model model) throws Exception {
	 * logger.info("list Page with Criteria");
	 * 
	 * model.addAttribute("list", boardService.listCriteria(cri)); }
	 */
	
	@RequestMapping(value="/listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception {
		
		logger.info(cri.toString());

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(cri);
		pageMaker.setTotlaCount(boardService.listCountCriteria(cri));
		
		model.addAttribute("list", boardService.listCriteria(cri));
		model.addAttribute("pageMaker", pageMaker);
	}
}
