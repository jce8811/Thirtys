package com.mycompany.thirtys.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.thirtys.vo.LoginDTO;
import com.mycompany.thirtys.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final String namespace = "com.mycompany.thirtys.mappers.userMapper";
	
	private final SqlSession session;
	
	@Inject
	public UserDAOImpl(SqlSession sqlSession) {
		this.session = sqlSession;
	}
	
	@Override
	public void join(UserVO userVO) throws Exception {
		session.insert(namespace + ".join", userVO);

	}

	@Override
	public UserVO login(LoginDTO loginDTO) throws Exception {
		return session.selectOne(namespace + ".login", loginDTO);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("uid", uid);
		paraMap.put("sessionId", sessionId);
		paraMap.put("next", next);
		
		session.update(namespace + ".keepLogin", paraMap);
	}

	@Override
	public UserVO checkWithSessionKey(String value) {
		return session.selectOne(namespace + ".checkWithSessionKey", value);
	}

	@Override
	public String checkId(String uid, String uemail) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("uid", uid);
		map.put("uemail", uemail);
		return session.selectOne(namespace + ".checkID", map);
	}
	@Override
	public void modifyPw(UserVO userVO) throws Exception {
		session.update(namespace + ".modifyPw", userVO);
	}

	@Override
	public UserVO info(String uid) throws Exception {
		return session.selectOne(namespace + ".info", uid);
	}
	
	@Override
	public int findPw(UserVO userVO) throws Exception {
		return session.update(namespace + ".findPw", userVO);
	}

	@Override
	public void sendEmail(UserVO userVO, String div) throws Exception {
		
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = "emma463@naver.com";
		String hostSMTPpwd = "tlsfkaus2516a";
		
		// 보내는 사람 email, 제목, 내용
		String fromEmail = "emma463@naver.com";
		String fromName = "30's에서 보내드립니다.";
		String subject = "";
		String msg = "";
		
		if(div.equals("findIdPw")){
			subject = "임시 비밀번호 입니다.";
			msg += "<div align = 'center' style='border:1px solid black;'>";
			msg += userVO.getUid();
			msg += "<p>임시 비밀번호 :";
			msg += userVO.getUpw();
		}
		// 받는 사람 E-Mail 주소
		String mail = userVO.getUemail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
				
	}
		

	
}
