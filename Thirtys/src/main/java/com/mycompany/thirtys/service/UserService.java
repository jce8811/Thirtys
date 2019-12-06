package com.mycompany.thirtys.service;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.mycompany.thirtys.vo.LoginDTO;
import com.mycompany.thirtys.vo.UserVO;

public interface UserService {
	
	void join(UserVO userVO) throws Exception;
	UserVO login(LoginDTO loginDTO) throws Exception;
	void keepLogin(String uid, String sessionId, Date next) throws Exception;
	UserVO checkWithSessionKey(String value);
	String checkId(String uid, String uemail) throws Exception;
	void modifyPw(UserVO userVO) throws Exception;
	UserVO info(String uid) throws Exception;
	void findIdPw(UserVO userVO, HttpServletResponse response) throws Exception;
}
