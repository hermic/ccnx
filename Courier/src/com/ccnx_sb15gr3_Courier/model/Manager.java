package com.ccnx_sb15gr3_Courier.model;

import java.util.List;

import org.ccnx.android.ccnlib.JsonMessage;



public class Manager extends JsonMessage implements java.io.Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private List<User> users;

public List<User> getUsers() {
	return users;
}

public void setUsers(List<User> users) {
	this.users = users;
}



}