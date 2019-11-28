package com.mycompany.thirtys.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycompany.thirtys.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final String namespace = "com.mycompany.thirtys.mappers.userMapper";
	
	private final SqlSession sqlSession;
	
	@Inject
	public UserDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void join(UserVO userVO) throws Exception {
		sqlSession.insert(namespace + ".join", userVO);

	}

}
