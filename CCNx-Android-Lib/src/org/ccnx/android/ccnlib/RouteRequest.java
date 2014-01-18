package org.ccnx.android.ccnlib;

import java.io.Serializable;
import java.util.Date;

public class RouteRequest extends JsonMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4363224884111152100L;
	private String start;
	private String koniec;
	private Date startDate;
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getKoniec() {
		return koniec;
	}
	public void setKoniec(String koniec) {
		this.koniec = koniec;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Date endDate;
	private double distance;
	private float fuel;
	private int userId;

}
