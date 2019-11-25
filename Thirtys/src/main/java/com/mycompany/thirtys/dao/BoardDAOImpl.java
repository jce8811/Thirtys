package com.mycompany.thirtys.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycompany.thirtys.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final String NAEMSPACE = "com.mycompany.thirtys.mappers.boardMapper";
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public void write(BoardVO boardVO) throws Exception {
		sqlSession.insert(NAEMSPACE + ".write", boardVO);

	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return sqlSession.selectOne(NAEMSPACE + ".read", bno);
	}

	@Override
	public void modify(BoardVO boardVO) throws Exception {
		sqlSession.update(NAEMSPACE + ".modify", boardVO);

	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete(NAEMSPACE + ".delete", bno);

	}

	@Override
	public List<BoardVO> list() throws Exception {
		return sqlSession.selectList(NAEMSPACE + ".list");
	}

}
