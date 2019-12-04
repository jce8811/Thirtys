package com.mycompany.thirtys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycompany.thirtys.vo.BoardVO;
import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final String namespace = "com.mycompany.thirtys.mappers.boardMapper";
	
	@Inject
	SqlSession session;
	
	@Override
	public void write(BoardVO boardVO) throws Exception {

		session.insert(namespace + ".write", boardVO);

	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void modify(BoardVO boardVO) throws Exception {
		session.update(namespace + ".modify", boardVO);

	}

	@Override
	public void delete(int bno) throws Exception {
		session.delete(namespace + ".delete", bno);

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
		return session.selectList(namespace + ".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace + ".list", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countPaging", cri);
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
		return session.selectList(namespace + ".listSearch", scri);
	}

	@Override
	public int countSearchPaging(SearchCriteria scri) throws Exception {
		return session.selectOne(namespace + ".countSearchPaging", scri);
	}

	@Override
	public void updateReplyCnt(int bno, int amount) throws Exception {
		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("amount", amount);
		
		session.update(namespace + ".updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(int bno) throws Exception {
		session.update(namespace + ".updateViewCnt", bno);
	}


}
