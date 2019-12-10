package com.mycompany.thirtys.service;


import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.thirtys.dao.UserDAO;
import com.mycompany.thirtys.vo.LoginDTO;
import com.mycompany.thirtys.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDAO userDAO;
	
	@Inject
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@Override
	public void join(UserVO userVO) throws Exception {
		userDAO.join(userVO);
	}
	@Override
	public UserVO login(LoginDTO loginDTO) throws Exception {
		return userDAO.login(loginDTO);
	}
	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {
		userDAO.keepLogin(uid, sessionId, next);
	}
	@Override
	public UserVO checkWithSessionKey(String value) {
		return userDAO.checkWithSessionKey(value);
	}
	@Override
	public String checkId(String uid, String uemail) throws Exception {
		return userDAO.checkId(uid, uemail);
		
	}
	
	@Override
	public void modifyPw(UserVO userVO) throws Exception {
		userDAO.modifyPw(userVO);
	}
	@Override
	public UserVO info(String uid) throws Exception {
		return userDAO.info(uid);
	}
	@Override
	public void findIdPw(UserVO userVO) throws Exception {
		userDAO.findIdPw(userVO);
		
	}
	@Override
	public String findId(String uid) throws Exception {
		return userDAO.findId(uid);
	}
	

}
