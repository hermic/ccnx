package com.ccnx_sb15gr3_Courier.model;

import java.util.Date;

import org.ccnx.android.ccnlib.JsonMessage;



public class RouteInformation extends JsonMessage {
	
	private Integer routeInformationId;
	public Integer getRouteInformationId() {
		return routeInformationId;
	}
	public void setRouteInformationId(Integer routeInformationId) {
		this.routeInformationId = routeInformationId;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public float getFuel() {
		return fuel;
	}
	public void setFuel(float fuel) {
		this.fuel = fuel;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	private Car car;
	private Route route;
	private User user;
	private double distance;
	private float fuel;
	private Date startTime;
	private Date endTime;

}
