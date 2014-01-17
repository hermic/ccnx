package Courier.CourierService.Models;

// Generated Jan 15, 2014 9:14:07 PM by Hibernate Tools 3.4.0.CR1

import java.beans.ConstructorProperties;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.ccnx.android.ccnlib.JsonMessage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Car generated by hbm2java
 */
@Entity
@Table(name = "Car", catalog = "CourierDB")
public class Car extends JsonMessage implements java.io.Serializable {

	private Integer carId;
	private String type;
	@JsonIgnore
	private Set<RouteInformation> routeInformations = new HashSet<RouteInformation>();
	@JsonIgnore
	private Set<User> users = new HashSet<User>(0);

	public Car() {
	}

	public Car(String type, Set<RouteInformation> routeInformations,
			Set<User> users) {
		this.type = type;
		this.routeInformations = routeInformations;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CarId", unique = true, nullable = false)
	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	@Column(name = "Type", columnDefinition = "enum('TruckA','TruckB')")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
	public Set<RouteInformation> getRouteInformations() {
		return this.routeInformations;
	}

	public void setRouteInformations(Set<RouteInformation> routeInformations) {
		this.routeInformations = routeInformations;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "cars")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
