package com.mycompany.thirtys.vo;

public class LoginDTO {
	
	private String uemail;
	private String upw;
	private boolean userCookie;
	
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public boolean isUserCookie() {
		return userCookie;
	}
	public void setUserCookie(boolean userCookie) {
		this.userCookie = userCookie;
	}
	
	@Override
	public String toString() {
		return "UserDTO [uemail=" + uemail + ", upw=" + upw + ", userCookie=" + userCookie + "]";
	}
		
}
