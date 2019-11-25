package com.mycompany.thirtys.service;

import java.util.List;

import com.mycompany.thirtys.vo.BoardVO;

public interface BoardService {
	
	void write(BoardVO boardVO) throws Exception;
	BoardVO read(int bno) throws Exception;
	void modify(BoardVO boardVO) throws Exception;
	void delete(int bno) throws Exception;
	List<BoardVO> list() throws Exception;
}
