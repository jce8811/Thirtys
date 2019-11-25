package com.mycompany.thirtys.dao;

import java.util.List;

import com.mycompany.thirtys.vo.BoardVO;

public interface BoardDAO {
	
	void write(BoardVO boardVO) throws Exception;
	BoardVO read(int bno) throws Exception;
	void modify(BoardVO boardVO) throws Exception;
	void delete(int bno) throws Exception;
	List<BoardVO> list() throws Exception;
}
