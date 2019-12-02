package com.mycompany.thirtys.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.thirtys.dao.BoardDAO;
import com.mycompany.thirtys.dao.ReplyDAO;
import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO replyDAO;
	@Inject
	private BoardDAO boardDAO;
	
	@Inject
	public ReplyServiceImpl(ReplyDAO replyDAO, BoardDAO boardDAO) {
		this.replyDAO = replyDAO;
	}
	@Transactional
	@Override
	public void write(ReplyVO replyVO) throws Exception {
		replyDAO.write(replyVO);
		boardDAO.updateReplyCnt(replyVO.getBno(), 1); // 댓글 갯수 증가
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
	@Transactional
	@Override
	public void delete(ReplyVO replyVO) throws Exception {
		int bno = replyDAO.getBno(replyVO.getRno()); // 댓글의 게시물 번호 조회
		replyDAO.delete(replyVO);
		boardDAO.updateReplyCnt(bno, -1); // 댓글 갯수 감소
		
	}

}
