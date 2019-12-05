package com.mycompany.thirtys.dao;

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

}
