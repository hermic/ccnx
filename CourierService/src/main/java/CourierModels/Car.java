package CourierModels;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Car")
public class Car implements Serializable
{
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue
private int CarId;

@Column(name="Type",
columnDefinition="enum('TruckA','TruckB')")
private String Type;

public String getType() {
	return Type;
}

public void setType(String type) {
	Type = type;
}

public Car()
{
	
}

public Car(String type)
{
	
}
}
