package com.mycompany.thirtys.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.thirtys.service.UserService;
import com.mycompany.thirtys.vo.LoginDTO;
import com.mycompany.thirtys.vo.UserVO;

@Controller
@RequestMapping("/user/")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
	@Inject
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// 회원가입 페이지
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void joinGET() throws Exception {
		
		logger.info("join GET");
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/joinPOST", method = RequestMethod.POST)
	public String joinPOST(UserVO userVO, RedirectAttributes rttr) throws Exception {
		
		String hashedPw = BCrypt.hashpw(userVO.getUpw(), BCrypt.gensalt());
		userVO.setUpw(hashedPw);
		userService.join(userVO);
		rttr.addFlashAttribute("msg", "join");

		return "redirect:/user/login";
	}
	
	// 회원정보 페이지
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public void infoGET(String uid, Model model) throws Exception {
		model.addAttribute("userVO", userService.info(uid));
		logger.info("info GET");
		
	}
	// 아이디&이메일 체크 페이지
	@RequestMapping(value = "/checkId", method = RequestMethod.GET)
	public void checkIdGET() throws Exception {
		logger.info("checkId GET");
		
	}
	// 아이디&이메일 체크
	@RequestMapping(value = "/checkIdPOST", method = RequestMethod.POST)
	public String checkIdPOST(String uid, String uemail, Model model) throws Exception {
		logger.info("checkId POST");
		model.addAttribute("upw", userService.checkId(uid, uemail));
		return "user/modifyPw";
	}
	// 비밀번호 변경
	@RequestMapping(value = "/modifyPwPOST", method = RequestMethod.POST)
	public String modifyPwPOST(String uid, String upw, UserVO userVO, RedirectAttributes rttr) throws Exception {
		logger.info("modifyPw POST");
		String hashedPw = BCrypt.hashpw(userVO.getUpw(), BCrypt.gensalt());
		userVO.setUpw(hashedPw);
		userService.modifyPw(userVO);
		rttr.addFlashAttribute("msg", "Msuccess");
		
		return "redirect:/user/logout";
	}
	
	// 내가 쓴글 페이지
	@RequestMapping(value = "/mywrite", method = RequestMethod.GET)
	public void mywriteGET() throws Exception {
		
		logger.info("mywrite GET");
	}
	// 아이디&비밀번호 찾기 페이지
		@RequestMapping(value="/findIdPw", method = RequestMethod.GET)
		public void findIdPw() throws Exception {
			logger.info("findIdPw GET");
		}
	// 아이디&비밀번호 찾기 페이지
	@RequestMapping(value="/findIdPwPOST", method = RequestMethod.POST)
	public void findIdPw(@ModelAttribute UserVO userVO, HttpServletResponse response) throws Exception {
		logger.info("findIdPw POST");
		userService.findIdPw(userVO, response);
	}
	
}
