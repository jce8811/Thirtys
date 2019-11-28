package com.mycompany.thirtys.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycompany.thirtys.vo.BoardVO;
import com.mycompany.thirtys.vo.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final String namespace = "com.mycompany.thirtys.mappers.boardMapper";
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public void write(BoardVO boardVO) throws Exception {
		sqlSession.insert(namespace + ".write", boardVO);

	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return sqlSession.selectOne(namespace + ".read", bno);
	}

	@Override
	public void modify(BoardVO boardVO) throws Exception {
		sqlSession.update(namespace + ".modify", boardVO);

	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete(namespace + ".delete", bno);

	}

	/*
	 * @Override public List<BoardVO> list() throws Exception { return
	 * sqlSession.selectList(namespace + ".list"); }
	 */

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1)*10;
		return sqlSession.selectList(namespace + ".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return sqlSession.selectList(namespace + ".list", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return sqlSession.selectOne(namespace + ".countPaging", cri);
	}


}
