package com.ccnx_sb15gr3_Courier.app;

import com.ccnx_sb15gr3_Courier.model.User;



public  class StaticUser {
	
	private static User user;

	public final static User getUser() {
		if(user!=null){
			return user;
		}else{
			return null;
		}
		
	
	}

	public final static void setUser(User user2) {
		user=user2;
	}
	
	

}
