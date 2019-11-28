package com.mycompany.thirtys.vo;

import java.util.Date;

public class UserVO {
	private String uemail;
	private String upw;
	private String uname;
	private int upoint;
	private String uaddr1;
	private String uaddr2;
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
	public String getUaddr1() {
		return uaddr1;
	}
	public void setUaddr1(String uaddr1) {
		this.uaddr1 = uaddr1;
	}
	public String getUaddr2() {
		return uaddr2;
	}
	public void setUaddr2(String uaddr2) {
		this.uaddr2 = uaddr2;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	
	@Override
	public String toString() {
		return "UserVO [uemail=" + uemail + ", upw=" + upw + ", uname=" + uname + ", upoint=" + upoint + ", uaddr1="
				+ uaddr1 + ", uaddr2=" + uaddr2 + ", udate=" + udate + "]";
	}
	
}
