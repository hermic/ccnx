package Courier.CourierService.Services;

import java.util.Date;
import java.util.List;

import Courier.CourierService.Models.Route;
import Courier.CourierService.Models.RouteInformation;
import Courier.CourierService.Models.User;

public interface RouteService {
	/**
	 * Zwraca wszystkie instancje tras.
	 * 
	 * @return
	 */
	List<RouteInformation> getAllRoutes();

	/**
	 * Dodaje nowy typ trasy.
	 * 
	 * @param route
	 */
	void addOrUpdateRoute(Route route);

	/**
	 * Dodaje nowa instancje trasy.
	 * 
	 * @param info
	 */
	void addRouteInstance(RouteInformation info);

	void addRoute(String start, String koniec, Date startDate, Date endDate,
			double distance, float fuel, int userId);

	/**
	 * Przejechane trasy danego usera.
	 * 
	 * @param user
	 *            - obiekt User
	 * @return
	 */
	List<RouteInformation> getRoutesForDriver(User user);

	/**
	 * Przejechane trasy danego usera.
	 * 
	 * @param userId
	 * @return
	 */
	List<RouteInformation> getRoutesForDriver(Integer userId);
}
