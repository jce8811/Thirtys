package com.mycompany.thirtys.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.mycompany.thirtys.vo.MemberVO;

public class MemberDAOimpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.mycompany.mappers.MemberMapper";

	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}
	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert(namespace+".insertMember", vo);
	}
}
