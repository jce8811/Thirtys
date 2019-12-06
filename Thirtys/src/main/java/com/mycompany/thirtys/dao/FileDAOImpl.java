package com.mycompany.thirtys.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class FileDAOImpl implements FileDAO {
	
	private static String namespace = "com.mycompany.thirtys.mappers.fileMapper";
	
	private final SqlSession session;
	
	@Inject
	public FileDAOImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public void addFile(String fullname) throws Exception {
		System.out.println(fullname);
		session.insert(namespace + ".addFile", fullname);
	}

	@Override
	public List<String> listFiles(int bno) throws Exception {
		return session.selectList(namespace + ".listFiles", bno); 
	}

	@Override
	public void deleteFiles(int bno) throws Exception {
		session.delete(namespace + ".deleteFiles", bno);
		
	}

}
