package com.mycompany.thirtys.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.thirtys.dao.BoardDAO;
import com.mycompany.thirtys.vo.BoardVO;
import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	BoardDAO boardDAO;

	@Override
	public void write(BoardVO boardVO) throws Exception {
		boardDAO.write(boardVO);

	}
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(int bno) throws Exception {
		return boardDAO.read(bno);
	}
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO readCnt(int bno) throws Exception {
		boardDAO.updateViewCnt(bno);
		return boardDAO.read(bno);
	}

	@Override
	public void modify(BoardVO boardVO) throws Exception {
		boardDAO.modify(boardVO);

	}

	@Override
	public void delete(int bno) throws Exception {
		boardDAO.delete(bno);

	}

	/*
	 * @Override 
	 * public List<BoardVO> list() throws Exception { 
	 * return boardDAO.list(); }
	 */

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return boardDAO.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return boardDAO.countPaging(cri);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria scri) throws Exception {
		return boardDAO.listSearch(scri);
	}

	@Override
	public int listSearchCount(SearchCriteria scri) throws Exception {
		return boardDAO.countSearchPaging(scri);
	}


}
