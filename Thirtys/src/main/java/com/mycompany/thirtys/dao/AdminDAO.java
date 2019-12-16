package com.mycompany.thirtys.dao;

import java.util.List;

import com.mycompany.thirtys.vo.SearchCriteria;
import com.mycompany.thirtys.vo.UserVO;

public interface AdminDAO {
	
	// 유저 리스트
	List<UserVO> userList(SearchCriteria scri) throws Exception;
	int countUserList(SearchCriteria scri) throws Exception;
}
