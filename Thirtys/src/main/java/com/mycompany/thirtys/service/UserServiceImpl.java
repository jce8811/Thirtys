package com.mycompany.thirtys.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

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

}
