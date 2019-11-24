package com.mycompany.thirtys;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MariaDBConnectionTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MariaDBConnectionTest.class);
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/thirtys";
	private static final String USER = "root";
	private static final String PASSWORD = "8811";
	
	@Test
	public void testConnection() throws Exception {
		Class.forName(DRIVER);
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);) {
			System.out.println(conn);
			System.out.println("연결되었습니다.");
			logger.info("연결되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
