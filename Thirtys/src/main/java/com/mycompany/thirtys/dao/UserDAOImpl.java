package com.mycompany.thirtys.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("uid", uid);
		paraMap.put("sessionId", sessionId);
		paraMap.put("next", next);
		
		session.update(namespace + ".keepLogin", paraMap);
	}

	@Override
	public UserVO checkWithSessionKey(String value) {
		return session.selectOne(namespace + ".checkWithSessionKey", value);
	}

	@Override
	public String checkId(String uid, String uemail) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("uid", uid);
		map.put("uemail", uemail);
		return session.selectOne(namespace + ".checkID", map);
	}
	@Override
	public void modifyPw(UserVO userVO) throws Exception {
		session.update(namespace + ".modifyPw", userVO);
	}

	@Override
	public UserVO info(String uid) throws Exception {
		return session.selectOne(namespace + ".info", uid);
	}
	
	@Override
	public int findIdPw(UserVO userVO) throws Exception {
		return session.update(namespace + ".findIdPw", userVO);
	}

	@Override
	public String findId(String uid) throws Exception {
		return session.selectOne(namespace + ".findId", uid);
	}

		

	
}
