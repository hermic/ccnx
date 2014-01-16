package com.ccnx_sb15gr3_Courier.model;

import java.util.HashSet;
import java.util.Set;

import org.ccnx.android.ccnlib.JsonMessage;



public class Route extends JsonMessage {

	private Integer routeId;
	private String startPoint;
	private String endPoint;
	private Set<RouteInformation> routeInformations = new HashSet<RouteInformation>(
			0);
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public String getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public Set<RouteInformation> getRouteInformations() {
		return routeInformations;
	}
	public void setRouteInformations(Set<RouteInformation> routeInformations) {
		this.routeInformations = routeInformations;
	}
	
}
