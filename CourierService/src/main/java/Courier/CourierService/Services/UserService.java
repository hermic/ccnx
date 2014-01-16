package Courier.CourierService.Services;

import java.util.List;

import Courier.CourierService.Models.Manager;
import Courier.CourierService.Models.User;

public interface UserService {
	
	/**
	 * Metoda bierze login i md5 hash do hasla,
	 *  
	 * @param login
	 * @param passwordMD5
	 * @return null jezeli uzytkownika nie ma w bazie - w przeciwnym wypadku obiekt User.
	 */
	User login(String login, String passwordMD5);
	
	/**
	 * 
	 * @return liste kierowcow
	 */
	Manager getAllDrivers();
	
	/**
	 * Dodaje usera.
	 * @param user
	 */
	void addUser(User user);
	
}
