package com.mycompany.thirtys.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	// 게시글 조회
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		// 저장된 쿠키 불러오기
		Cookie[] cookieFromRequest = request.getCookies();
		String cookieValue = null;
		for(int i = 0; i<cookieFromRequest.length; i++) {
			// 요청정보로부터 쿠키를 가져온다.
			cookieValue = cookieFromRequest[0].getValue();
		}
		
		// 쿠키 세션 입력
		if(session.getAttribute(bno + ":cookie") == null) {
			session.setAttribute(bno + ":cookie", bno + ":" + cookieValue);
		}else {
			session.setAttribute(bno + ":cookie ex", session.getAttribute(bno + ":cookie"));
			if(!session.getAttribute(bno + ":cookie").equals(bno + ":" + cookieValue)) {
				session.setAttribute(bno + ":cookie", bno + ":" + cookieValue);
			}
		}

		model.addAttribute("boardVO", boardService.read(bno));;
		logger.info("readPaging GET");
		
		if(!session.getAttribute(bno + ":cookie").equals(session.getAttribute(bno + ":cookie ex"))) {
			model.addAttribute("boardVO", boardService.readCnt(bno));;
		}
	}
	// 게시글 수정 페이지
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void modifyPageGET(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		
		logger.info("modifyPage GET");
		model.addAttribute("boardVO", boardService.read(bno));
	}
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		
		logger.info("modifyPage POST");
		
		boardService.modify(boardVO);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("serchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		rttr.addFlashAttribute("msg", "Msuccess");
		
		return "redirect:/gallery/list";
	}
	
	// 게시글 삭제
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deletePage(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		
		logger.info("deletePage POST");
		
		boardService.delete(bno);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("serchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		rttr.addFlashAttribute("msg", "Dsuccess");
		
		return "redirect:/gallery/list";
	}
	
}
