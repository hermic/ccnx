package com.ccnx_sb15gr3_Courier.model;

import org.ccnx.android.ccnlib.JsonMessage;

public class User extends JsonMessage{
	
	private String login;
	private String password;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
