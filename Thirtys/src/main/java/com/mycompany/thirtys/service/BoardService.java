package com.mycompany.thirtys.service;

import java.util.List;

import com.mycompany.thirtys.vo.BoardVO;
import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.SearchCriteria;

public interface BoardService {
	
	void write(BoardVO boardVO) throws Exception;
	BoardVO read(int bno) throws Exception;
	void modify(BoardVO boardVO) throws Exception;
	void delete(int bno) throws Exception;

	/* List<BoardVO> list() throws Exception; */
	List<BoardVO> listCriteria(Criteria cri) throws Exception;
	int listCountCriteria(Criteria cri) throws Exception;
	List<BoardVO> listSearchCriteria(SearchCriteria scri) throws Exception;
	int listSearchCount(SearchCriteria scri) throws Exception;
}
