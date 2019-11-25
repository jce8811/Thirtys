package com.mycompany.thirtys.service;

import java.util.List;

import com.mycompany.thirtys.vo.BoardVO;

public interface BoardService {
	
	void create(BoardVO boardVO) throws Exception;
	BoardVO read(int bno) throws Exception;
	void update(BoardVO boardVO) throws Exception;
	void delete(int bno) throws Exception;
	List<BoardVO> list() throws Exception;
}
