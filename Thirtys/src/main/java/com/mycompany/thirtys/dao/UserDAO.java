package com.mycompany.thirtys.dao;

import com.mycompany.thirtys.vo.UserVO;

public interface UserDAO {
	
	// 회원가입 처리
	void join(UserVO userVO) throws Exception;
}
