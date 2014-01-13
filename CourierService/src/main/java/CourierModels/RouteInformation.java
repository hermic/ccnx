package CourierModels;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RouteInformation")
public class RouteInformation implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int RouteInformationId;
	
	@Column(name="Distance")
	private double Distance;
	
	@Column(name="Fuel")
	private float Fuel;
	
	@Column(name="StartTime")
	private Date StartTime;
	
	@Column(name="EndTime")
	private Date EndTime;
	
	@ManyToOne
	@JoinColumn(name="RouteId")
	private Route Route;
	
	@ManyToOne
	@JoinColumn(name="CarId")
	private Car Car;
	
	@ManyToOne
	@JoinColumn(name="UserId")
	private User User;
	
	public double getDistance() {
		return Distance;
	}

	public void setDistance(double distance) {
		Distance = distance;
	}

	public float getFuel() {
		return Fuel;
	}

	public void setFuel(float fuel) {
		Fuel = fuel;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Date getEndTime() {
		return EndTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public Route getRoute() {
		return Route;
	}

	public void setRoute(Route route) {
		Route = route;
	}

	public Car getCar() {
		return Car;
	}

	public void setCar(Car car) {
		Car = car;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public RouteInformation()
	{
		
	}
}
