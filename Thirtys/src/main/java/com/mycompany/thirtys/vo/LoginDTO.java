package com.mycompany.thirtys.vo;

public class LoginDTO {
	
	private String uemail;
	private String upw;
	private boolean useCookie;
	
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
		return useCookie;
	}
	public void setUserCookie(boolean userCookie) {
		this.useCookie = userCookie;
	}
	
	@Override
	public String toString() {
		return "UserDTO [uemail=" + uemail + ", upw=" + upw + ", useCookie=" + useCookie + "]";
	}
		
}
