package com.mycompany.thirtys.service;

import java.util.List;

import com.mycompany.thirtys.vo.SearchCriteria;
import com.mycompany.thirtys.vo.UserVO;

public interface AdminService {
	
	// 유저 리스트
	List<UserVO> userList(SearchCriteria scri) throws Exception;
	int countUserList(SearchCriteria scri) throws Exception;
}
