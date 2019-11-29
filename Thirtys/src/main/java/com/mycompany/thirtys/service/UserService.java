package com.mycompany.thirtys.service;

import java.util.Date;

import com.mycompany.thirtys.vo.LoginDTO;
import com.mycompany.thirtys.vo.UserVO;

public interface UserService {
	
	void join(UserVO userVO) throws Exception;
	UserVO login(LoginDTO loginDTO) throws Exception;
	void keepLogin(String uemail, String sessionId, Date next) throws Exception;
	UserVO checkWithSessionKey(String value);
}
