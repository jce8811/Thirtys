package com.mycompany.thirtys.dao;

import java.util.List;

import com.mycompany.thirtys.vo.BoardVO;
import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.SearchCriteria;

public interface BoardDAO {
	
	/* 글작성 */
	void write(BoardVO boardVO) throws Exception;	
	/* 글조회 */
	BoardVO read(int bno) throws Exception;
	/* 글수정 */
	void modify(BoardVO boardVO) throws Exception;
	/* 글삭제 */
	void delete(int bno) throws Exception;
	/* 글리스트 */
	/* List<BoardVO> list() throws Exception; */
	/* 페이징 */
	List<BoardVO> listPage(int page) throws Exception;
	List<BoardVO> listCriteria(Criteria cri) throws Exception;
	int countPaging(Criteria cri) throws Exception;
	List<BoardVO> listSearch(SearchCriteria scri) throws Exception;
	int countSearchPaging(SearchCriteria scri) throws Exception;
	void updateReplyCnt(int bno, int amount) throws Exception;
	void updateViewCnt(int bno) throws Exception;
}
