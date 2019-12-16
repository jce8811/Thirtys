package com.mycompany.thirtys.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycompany.thirtys.vo.SearchCriteria;
import com.mycompany.thirtys.vo.UserVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	private static final String namespace = "com.mycompany.thirtys.mappers.adminMapper";
	
	@Inject
	SqlSession session;
	
	@Override
	public List<UserVO> userList(SearchCriteria scri) throws Exception {
		return session.selectList(namespace + ".userList", scri);
	}

	@Override
	public int countUserList(SearchCriteria scri) throws Exception {
		return session.selectOne(namespace + ".countUserList", scri);
	}

}
