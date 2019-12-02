package com.mycompany.thirtys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.thirtys.service.ReplyService;
import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.PageMaker;
import com.mycompany.thirtys.vo.ReplyVO;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Inject
	private ReplyService replyService;
	
	// 댓글 작성
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> write(@RequestBody ReplyVO replyVO){
		
		logger.info("write POST");
		
		ResponseEntity<String> entity = null;
		try {
			replyService.write(replyVO);
			entity = new ResponseEntity<>("writeSuccess", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 댓글 리스트
	@RequestMapping(value="/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno) {
		
		logger.info("list GET");
		
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<>(replyService.list(bno), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// 댓글 수정
	@RequestMapping(value="/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> modify(@PathVariable("rno") int rno, @RequestBody ReplyVO replyVO) {
		
		logger.info("modify PUT,PATCH");
		
		ResponseEntity<String> entity = null;
		try {
			replyVO.setRno(rno);
			replyService.modify(replyVO);
			entity = new ResponseEntity<>("modifySuccess", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// 댓글 삭제
	@RequestMapping(value="/{rno}", method = RequestMethod.POST)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno, @RequestBody ReplyVO replyVO){
		
		logger.info("delete POST");
		
		ResponseEntity<String> entity = null;
		try {
			replyVO.setRno(rno);
			replyService.delete(replyVO);
			entity = new ResponseEntity<>("deleteSuccess", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 댓글 페이징 처리
	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listPage(@PathVariable("bno") int bno, @PathVariable("page") int page) {
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCriteria(cri);
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<ReplyVO> list = replyService.listPage(bno, cri);
			
			map.put("list", list);
			
			int replyCount = replyService.count(bno);
			pageMaker.setTotlaCount(replyCount);
			
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
