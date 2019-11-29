package com.mycompany.thirtys.vo;

import java.util.Date;

public class UserVO {
	private String uemail;
	private String upw;
	private String uname;
	private int upoint;
	private Date udate;
	
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
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	
	@Override
	public String toString() {
		return "UserVO [uemail=" + uemail + ", upw=" + upw + ", uname=" + uname + ", upoint=" + upoint + ", udate=" + udate + "]";
	}
	
}
