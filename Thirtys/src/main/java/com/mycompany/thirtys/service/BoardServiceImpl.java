package com.mycompany.thirtys.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mycompany.thirtys.dao.BoardDAO;
import com.mycompany.thirtys.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	BoardDAO boardDAO;

	@Override
	public void create(BoardVO boardVO) throws Exception {
		boardDAO.create(boardVO);

	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return boardDAO.read(bno);
	}

	@Override
	public void update(BoardVO boardVO) throws Exception {
		boardDAO.update(boardVO);

	}

	@Override
	public void delete(int bno) throws Exception {
		boardDAO.delete(bno);

	}

	@Override
	public List<BoardVO> list() throws Exception {
		return boardDAO.list();
	}

}
