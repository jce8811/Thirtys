package com.mycompany.thirtys.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.util.WebUtils;

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
	public void LoginPOST(LoginDTO loginDTO, HttpSession session, Model model) throws Exception {
		
		logger.info("login POST");
		
		UserVO userVO = userService.login(loginDTO);
		
		if (userVO == null || !BCrypt.checkpw(loginDTO.getUpw(), userVO.getUpw())) {
			return;
		}
		model.addAttribute("userVO", userVO);

		// 로그인 유지를 선택할 경우
		if(loginDTO.isUserCookie()) {
			
			int amount = 60 * 60 * 24 * 7;
			Date sessionlimit = new Date(System.currentTimeMillis()+(1000*amount));
			userService.keepLogin(userVO.getUemail(), session.getId(), sessionlimit);
		}
	}
	// 로그아웃 처리
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		Object object = session.getAttribute("login");
		
		if(object != null) {
			UserVO userVO = (UserVO) object;
			
			session.removeAttribute("login");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				userService.keepLogin(userVO.getUemail(), session.getId(), new Date());
			}
		}
		return "user/logout";
	}
}
