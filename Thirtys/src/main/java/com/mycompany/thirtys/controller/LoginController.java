package com.mycompany.thirtys.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.thirtys.service.UserService;
import com.mycompany.thirtys.vo.LoginDTO;
import com.mycompany.thirtys.vo.UserVO;

@Controller
@RequestMapping("/user")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private final UserService userService;
	
	@Inject
	public LoginController(UserService userService) {
		this.userService = userService;
	}
	

	// 로그인 페이지
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void LoginGET(@ModelAttribute("loginDTO") LoginDTO loginDTO) throws Exception {
		
		logger.info("login GET");
	}
	// 로그인 처리
	@RequestMapping(value = "/loginPOST", method = RequestMethod.POST)
	public void LoginPOST(LoginDTO loginDTO, HttpSession httpSession, Model model) throws Exception {
		
		logger.info("login POST");
		
		UserVO userVO = userService.login(loginDTO);
		
		if (userVO == null || !BCrypt.checkpw(loginDTO.getUpw(), userVO.getUpw())) {
			return;
		}
		model.addAttribute("userVO", userVO);
	}
}
