package Courier.CourierService.Models;

import java.io.Serializable;
import java.util.List;

import org.ccnx.android.ccnlib.JsonMessage;

public class RouteList extends JsonMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<RouteInformation> userList;

	public List<RouteInformation> getUserList() {
		return userList;
	}

	public void setUserList(List<RouteInformation> userList) {
		this.userList = userList;
	}
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	private String user;
	private int userId;
	
	public void setUserID(int userId){
		this.userId=userId;
	}
	
	public int getUserId() {
		// TODO Auto-generated method stub
		return userId;
	}
	
	
}
