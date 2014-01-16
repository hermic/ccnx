package Courier.CourierService.Models;

import java.util.List;

import org.ccnx.android.ccnlib.JsonMessage;

public class Manager extends JsonMessage implements java.io.Serializable {
private List<User> users;

public List<User> getUsers() {
	return users;
}

public void setUsers(List<User> users) {
	this.users = users;
}



}
