package Courier.CourierService.Services;

import java.util.List;

import Courier.CourierService.Models.*;

public interface RouteService {
     /**
      * Zwraca wszystkie instancje tras.
      * @return
      */
	 List<RouteInformation> getAllRoutes();
	 
	 /**
	  * Dodaje nowy typ trasy.
	  * @param route
	  */
	 void addOrUpdateRoute(Route route);
	 
	 /**
	  * Dodaje nowa instancje trasy.
	  * @param info
	  */
	 void addRouteInstance(RouteInformation info);
	 
	 /**
	  * Przejechane trasy danego usera.
	  * @param user -  obiekt User
	  * @return
	  */
	 List<RouteInformation> getRoutesForDriver(User user);
	 
	 /**
	  * Przejechane trasy danego usera.
	  * @param userId
	  * @return
	  */
	 List<RouteInformation> getRoutesForDriver(Integer userId);
}
