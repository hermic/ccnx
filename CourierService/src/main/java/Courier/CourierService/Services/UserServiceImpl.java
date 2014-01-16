package Courier.CourierService.Services;

import java.util.List;

import Courier.CourierService.Models.User;
import Courier.CourierService.Models.UserDAO;
import Courier.CourierService.Models.UserDAO.UserType;

public class UserServiceImpl implements UserService {
private UserDAO userDAO = new UserDAO();
	
	public User login(String login, String passwordMD5) {  
        User user = userDAO.findByName(login, passwordMD5);
        if (user != null)
        {
        	return user;
        }
        
		return null;
	}

	public List<User> getAllDrivers() {
		List<User> users = userDAO.findByType(UserType.Kierowca);
		return users;
	}

	public void addUser(User user) {
		userDAO.save(user);
		
	}

}
