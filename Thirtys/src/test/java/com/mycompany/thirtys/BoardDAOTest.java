package com.mycompany.thirtys;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.thirtys.dao.BoardDAO;
import com.mycompany.thirtys.vo.BoardVO;
import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Inject
	private BoardDAO boardDAO;
	
	@Test
	public void testWrite() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("새로운 글을 넣습니다.");
		boardVO.setContent("새로운 글을 넣습니다.");
		boardVO.setWriter("user00");
		boardDAO.write(boardVO);
	}
	
	@Test
	public void testRead() throws Exception {
		logger.info(boardDAO.read(1).toString());
	}
	
	@Test
	public void testModify() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(1);
		boardVO.setTitle("수정된 글입니다.");
		boardVO.setContent("수정 테스트");
		boardDAO.modify(boardVO);
	}
	@Test
	public void testDelete() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(1);
		boardVO.setState("D");
		boardDAO.delete(1);
	}
	
	@Test
	public void testListPage() throws Exception{
		
		SearchCriteria scri = new SearchCriteria();
		scri.setPage(3);
		scri.setPerPageNum(20);
		
		List<BoardVO> list = boardDAO.listSearch(scri);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
	
	@Test
	public void testListCriteria() throws Exception{
		
		SearchCriteria scri = new SearchCriteria();
		scri.setPage(3);
		scri.setPerPageNum(20);
		
		List<BoardVO> list = boardDAO.listSearch(scri);
				
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
}
