package com.mycompany.thirtys.dao;

import java.util.List;

public interface FileDAO {
	
	//파일 추가
	void addFile(String fullname) throws Exception;
	// 첨부 파일 목록
	List<String> listFiles(int bno) throws Exception;
	// 게시글과 함꼐 삭제
	void deleteFiles(int bno) throws Exception;
}
