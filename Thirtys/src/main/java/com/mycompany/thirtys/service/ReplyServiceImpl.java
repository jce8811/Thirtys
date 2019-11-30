package com.mycompany.thirtys.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mycompany.thirtys.dao.ReplyDAO;
import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO replyDAO;
	
	@Override
	public void write(ReplyVO replyVO) throws Exception {
		replyDAO.write(replyVO);
	}

	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		return replyDAO.list(bno);
	}

	@Override
	public void modify(ReplyVO replyVO) throws Exception {
		replyDAO.modify(replyVO);
	}

	@Override
	public List<ReplyVO> listPage(int bno, Criteria cri) throws Exception {
		return replyDAO.listPage(bno, cri);
	}

	@Override
	public int count(int bno) throws Exception {
		return replyDAO.count(bno);
	}

}
