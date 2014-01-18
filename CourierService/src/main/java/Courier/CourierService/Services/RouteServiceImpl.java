package Courier.CourierService.Services;

import java.util.Date;
import java.util.List;

import Courier.CourierService.Models.GenericDAO;
import Courier.CourierService.Models.Route;
import Courier.CourierService.Models.RouteDAO;
import Courier.CourierService.Models.RouteInformation;
import Courier.CourierService.Models.RouteInformationDAO;
import Courier.CourierService.Models.User;

public class RouteServiceImpl implements RouteService{
private RouteInformationDAO routeInformationDAO = new RouteInformationDAO();
private RouteDAO routeDAO = new RouteDAO();
	
	public List<RouteInformation> getAllRoutes() {
		List<RouteInformation> routes = routeInformationDAO.findAll();
		return routes;
	}

	public void addOrUpdateRoute(Route route) {
		routeDAO.save(route);
		
	}

	public void addRouteInstance(RouteInformation info) {
		routeInformationDAO.save(info);
		
	}

	public List<RouteInformation> getRoutesForDriver(User user) {
		List<RouteInformation> informationList = null;
		if (user.getRouteInformations().isEmpty())
		{
			informationList = routeInformationDAO.findByUser(user);
		}
		else
		{
			informationList.addAll(user.getRouteInformations());
		}
		
		return informationList;
	}
	public List<RouteInformation> getRoutesForDriver(Integer userId) {
		
		return routeInformationDAO.findByUserId(userId);
	}

	public void addRoute(String start, String koniec, Date startDate,
			Date endDate, double distance, float fuel, int userId) {
		// TODO Auto-generated method stub
		
	}}


