package com.mycompany.thirtys.service;

import java.util.List;

import com.mycompany.thirtys.vo.Criteria;
import com.mycompany.thirtys.vo.ReplyVO;

public interface ReplyService {

	void write(ReplyVO replyVO) throws Exception;
	List<ReplyVO> list(int bno) throws Exception;
	void modify(ReplyVO replyVO) throws Exception;
	List<ReplyVO> listPage(int bno, Criteria cri) throws Exception;
	int count(int bno) throws Exception;
}
