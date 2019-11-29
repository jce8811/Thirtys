package com.mycompany.thirtys.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycompany.thirtys.vo.LoginDTO;
import com.mycompany.thirtys.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final String namespace = "com.mycompany.thirtys.mappers.userMapper";
	
	private final SqlSession session;
	
	@Inject
	public UserDAOImpl(SqlSession sqlSession) {
		this.session = sqlSession;
	}
	
	@Override
	public void join(UserVO userVO) throws Exception {
		session.insert(namespace + ".join", userVO);

	}

	@Override
	public UserVO login(LoginDTO loginDTO) throws Exception {
		return session.selectOne(namespace + ".login", loginDTO);
	}

	@Override
	public void keepLogin(String uemail, String sessionId, Date next) throws Exception {
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("uemail", uemail);
		paraMap.put("sessionId", sessionId);
		paraMap.put("next", next);
		
		session.update(namespace + ".keepLogin", paraMap);
	}

	@Override
	public UserVO checkWithSessionKey(String value) {
		return session.selectOne(namespace + ".checkWithSessionKey", value);
	}
	
}
