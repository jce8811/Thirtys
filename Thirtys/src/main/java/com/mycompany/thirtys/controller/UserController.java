package com.mycompany.thirtys.controller;

import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.thirtys.service.UserService;
import com.mycompany.thirtys.vo.UserVO;

@Controller
@RequestMapping("/user")
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
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(UserVO userVO, RedirectAttributes rttr) throws Exception {
		
		String hashedPw = BCrypt.hashpw(userVO.getUpw(), BCrypt.gensalt());
		userVO.setUpw(hashedPw);
		userService.join(userVO);
		rttr.addFlashAttribute("msg", "join");
		
		return "redirect:/user/login";
	}
	
	// 회원정보 페이지
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public void infoGET() throws Exception {
		
		logger.info("info GET");
	}
	
	// 내가 쓴글 페이지
	@RequestMapping(value = "/mywrite", method = RequestMethod.GET)
	public void mywriteGET() throws Exception {
		
		logger.info("mywrite GET");
	}
}
