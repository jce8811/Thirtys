package com.mycompany.thirtys.dao;

import java.util.List;

import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.ReplyVO;

public interface ReplyDAO {
	
	List<ReplyVO> list(int bno) throws Exception;
	void write(ReplyVO replyVO) throws Exception;
	void modify(ReplyVO replyVO) throws Exception;
	void delete(ReplyVO replyVO) throws Exception;
	List<ReplyVO> listPage(int bno, Criteria cri) throws Exception;
	int count(int bno) throws Exception;
	int getBno(int rno) throws Exception;
}
