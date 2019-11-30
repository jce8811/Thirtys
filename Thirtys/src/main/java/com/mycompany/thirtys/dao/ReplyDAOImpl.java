package com.mycompany.thirtys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	private static String namespace = "com.mycompany.thirtys.mappers.replyMapper";
	
	@Inject
	private SqlSession session;
	
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		
		return session.selectList(namespace + ".list", bno);
	}

	@Override
	public void write(ReplyVO replyVO) throws Exception {
		session.insert(namespace + ".write", replyVO);
	}

	@Override
	public void modify(ReplyVO replyVO) throws Exception {
		session.update(namespace + ".modify", replyVO);
		
	}

	@Override
	public void delete(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReplyVO> listPage(int bno, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("cri", cri);
		
		return session.selectList(namespace + ".listPage", paramMap);
	}

	@Override
	public int count(int bno) throws Exception {
		return session.selectOne(namespace + ".count", bno);
	}

}
