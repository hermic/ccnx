package Courier.CourierService.Services;

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
	
	@Override
	public List<RouteInformation> getAllRoutes() {
		List<RouteInformation> routes = routeInformationDAO.findAll();
		return routes;
	}

	@Override
	public void addOrUpdateRoute(Route route) {
		routeDAO.save(route);
		
	}

	@Override
	public void addRouteInstance(RouteInformation info) {
		routeInformationDAO.save(info);
		
	}

	@Override
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

	@Override
	public List<RouteInformation> getRoutesForDriver(Integer userId) {
		
		return routeInformationDAO.findByUserId(userId);
	}

}
