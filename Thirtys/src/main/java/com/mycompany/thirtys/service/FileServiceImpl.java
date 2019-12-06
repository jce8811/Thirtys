package com.mycompany.thirtys.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mycompany.thirtys.dao.FileDAO;

@Service
public class FileServiceImpl implements FileService {
	
	private final FileDAO fileDAO;
	
	@Inject
	public FileServiceImpl(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	// 첨부 파일 목록
	@Override
	public List<String> listFiles(int bno) throws Exception {
		return fileDAO.listFiles(bno);
	}

}
