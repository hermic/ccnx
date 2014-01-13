package CourierModels;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Route")
public class Route implements Serializable
{
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue
private int RouteId;
	
@Column(name="From")
private String From;

@Column(name="To")
private String To;

public Route()
{
	
}

public Route(String from, String to)
{
	this.From = from;
	this.To = to;
}

@Override
public String toString()
{
	return "Z " + this.From + " do " + this.To;
}

public int getRouteId() {
	return RouteId;
}

public void setRouteId(int routeId) {
	RouteId = routeId;
}

public String getFrom() {
	return From;
}

public void setFrom(String from) {
	From = from;
}

public String getTo() {
	return To;
}

public void setTo(String to) {
	To = to;
}

}
