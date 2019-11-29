package com.mycompany.thirtys.service;

import com.mycompany.thirtys.vo.LoginDTO;
import com.mycompany.thirtys.vo.UserVO;

public interface UserService {
	
	void join(UserVO userVO) throws Exception;
	UserVO login(LoginDTO loginDTO) throws Exception;
}
