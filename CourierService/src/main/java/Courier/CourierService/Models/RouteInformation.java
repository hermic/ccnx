package Courier.CourierService.Models;

// Generated Jan 15, 2014 9:14:07 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RouteInformation generated by hbm2java
 */
@Entity
@Table(name = "RouteInformation", catalog = "CourierDB")
public class RouteInformation implements java.io.Serializable {

	private Integer routeInformationId;
	private Car car;
	private Route route;
	private User user;
	private double distance;
	private float fuel;
	private Date startTime;
	private Date endTime;

	public RouteInformation() {
	}

	public RouteInformation(Car car, Route route, User user, double distance,
			float fuel, Date startTime, Date endTime) {
		this.car = car;
		this.route = route;
		this.user = user;
		this.distance = distance;
		this.fuel = fuel;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RouteInformationId", unique = true, nullable = false)
	public Integer getRouteInformationId() {
		return this.routeInformationId;
	}

	public void setRouteInformationId(Integer routeInformationId) {
		this.routeInformationId = routeInformationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CarId", nullable = false)
	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RouteId", nullable = false)
	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "Distance", nullable = false, precision = 22, scale = 0)
	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Column(name = "Fuel", nullable = false, precision = 12, scale = 0)
	public float getFuel() {
		return this.fuel;
	}

	public void setFuel(float fuel) {
		this.fuel = fuel;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "StartTime", nullable = false, length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndTime", nullable = false, length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
