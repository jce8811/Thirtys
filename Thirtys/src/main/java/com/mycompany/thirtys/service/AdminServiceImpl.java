package com.mycompany.thirtys.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mycompany.thirtys.dao.AdminDAO;
import com.mycompany.thirtys.vo.SearchCriteria;
import com.mycompany.thirtys.vo.UserVO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Inject
	private AdminDAO adminDAO;
	
	@Override
	public List<UserVO> userList(SearchCriteria scri) throws Exception {
		return adminDAO.userList(scri);
	}

	@Override
	public int countUserList(SearchCriteria scri) throws Exception {
		return adminDAO.countUserList(scri);
	}

}
