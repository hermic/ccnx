package com.ccnx_sb15gr3_Courier.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.ccnx.android.ccnlib.JsonMessage;



public class User extends JsonMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -373945999108016882L;
	private String login;
	private String password;
	private Integer userId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<RouteInformation> getRouteInformations() {
		return routeInformations;
	}
	public void setRouteInformations(Set<RouteInformation> routeInformations) {
		this.routeInformations = routeInformations;
	}
	public Set<Car> getCars() {
		return cars;
	}
	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	private String email;
	private String type;
	private Set<RouteInformation> routeInformations = new HashSet<RouteInformation>(
			0);
	private Set<Car> cars = new HashSet<Car>(0);
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
