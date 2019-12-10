package com.mycompany.thirtys.commons;


import org.apache.commons.mail.HtmlEmail;

public class MailUtils {
	
	public static void sendEmail(String email, String subject, String msg) throws Exception {
		
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = "emma463@naver.com";
		String hostSMTPpwd = "tlsfkaus2516a";
		
		// 보내는 사람
		String fromEmail = "emma463@naver.com";
		String fromName = "30's";
		
		// email 전송
		try {
			HtmlEmail mail = new HtmlEmail();
			mail.setDebug(true);
			mail.setCharset(charSet);
			mail.setSSLOnConnect(true); // SSL 사용(TLS가 없는 경우 SSL 사용)
			mail.setHostName(hostSMTP);
			mail.setSmtpPort(465);
			mail.setAuthentication(hostSMTPid, hostSMTPpwd);
			mail.setStartTLSEnabled(true);
			mail.addTo(email);
			mail.setFrom(fromEmail, fromName, charSet);
			mail.setSubject(subject);
			mail.setHtmlMsg(msg);
			mail.send();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String newPw() throws Exception {
		StringBuffer newPw = new StringBuffer();
		
		for (int i = 0; i < 12; i++) {
			newPw.append((char) ((Math.random() * 26) + 97));
		}
		return newPw.toString();
	}
	
}
