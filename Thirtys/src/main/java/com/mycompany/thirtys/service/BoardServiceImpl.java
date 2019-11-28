package com.mycompany.thirtys.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mycompany.thirtys.dao.BoardDAO;
import com.mycompany.thirtys.vo.BoardVO;
import com.mycompany.thirtys.vo.Criteria;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	BoardDAO boardDAO;

	@Override
	public void write(BoardVO boardVO) throws Exception {
		boardDAO.write(boardVO);

	}

	@Override
	public BoardVO read(int bno) throws Exception {
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


}
