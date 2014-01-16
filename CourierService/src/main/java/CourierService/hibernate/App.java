package CourierService.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Courier.CourierService.Models.Route;
import Courier.CourierService.Models.RouteInformation;
import Courier.CourierService.Models.User;
import Courier.CourierService.Models.UserDAO;
import Courier.CourierService.Services.RouteService;
import Courier.CourierService.Services.RouteServiceImpl;
import Courier.CourierService.Services.UserService;
import Courier.CourierService.Services.UserServiceImpl;

public class App {
	public static void main(String[] args) {
		// SessionFactory sf = HibernateUtil.getSessionFactory();
		// Session session = sf.openSession();
		// session.beginTransaction();
		UserService serwis = new UserServiceImpl();
		//List<User> list = serwis.getAllDrivers();
		//Set<RouteInformation> infos = list.get(1).getRouteInformations();
		//List<RouteInformation> bla = new ArrayList<RouteInformation>(infos);
		//System.out.print("asd");
		//		User userek = new User();
//		userek.setLogin("driverB");
//		userek.setPassword("123");
//		userek.setType("Kierowca");
//		userek.setEmail("test@");
//		serwis.addUser(userek);
//		User user = serwis.login("test", "202cb962ac59075b964b07152d234b70");
//		System.out.print(user.getEmail() + "\n");
//		RouteService routeSerwis = new RouteServiceImpl();
//		List<RouteInformation> all = routeSerwis.getRoutesForDriver(userek);
//		List<RouteInformation> all = routeSerwis.getAllRoutes();
//		for (RouteInformation info : all) {
//			System.out.print(info.getFuel() + "\n");
//		}
//
		//Route route = new Route();
		//route.setStartPoint("hibernated");
		//route.setEndPoint("blablad");
		//routeSerwis.addOrUpdateRoute(route);
		// List<User> users = serwis.getAllDrivers();
		// for(User us : users)
		// {
		// System.out.print(us.getEmail());
		// }
	}
}
