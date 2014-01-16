package com.ccnx_sb15gr3_Courier.model;

import java.util.HashSet;
import java.util.Set;

import org.ccnx.android.ccnlib.JsonMessage;



public class Car extends JsonMessage {

	private Integer carId;
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
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
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	private String type;
	private Set<RouteInformation> routeInformations = new HashSet<RouteInformation>(
			0);
	private Set<User> users = new HashSet<User>(0);


}
