package com.mycompany.thirtys.dao;

import java.util.Date;

import com.mycompany.thirtys.vo.LoginDTO;
import com.mycompany.thirtys.vo.UserVO;

public interface UserDAO {
	
	// 회원가입 처리
	void join(UserVO userVO) throws Exception;
	// 로그인 처리
	UserVO login(LoginDTO loginDTO) throws Exception;
	// 로그인 유지 처리
	void keepLogin(String uid, String sessionId, Date next) throws Exception;
	// 세션키 검증
	UserVO checkWithSessionKey(String value);
	
	String checkId(String uid, String uemail) throws Exception;
	void modifyPw(UserVO userVO) throws Exception;
	UserVO info(String uid) throws Exception;
	int findPw(UserVO userVO) throws Exception;
	void sendEmail(UserVO userVo, String div) throws Exception;
}
