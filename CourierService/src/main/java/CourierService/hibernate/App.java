package CourierService.hibernate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Courier.CourierService.Models.Car;
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
	//	User user =serwis.login("test2", "123");
		
		//System.out.println(user.getLogin());
		//List<User> list = serwis.getAllDrivers();
		//Set<RouteInformation> infos = list.get(1).getRouteInformations();
		//List<RouteInformation> bla = new ArrayList<RouteInformation>(infos);
		//System.out.print("asd");
	/*	User userek = new User();
		userek.setLogin("ja");
		userek.setPassword("123");
		userek.setType("Kierowca");

		userek.setEmail("test@");
		serwis.addUser(userek);*/
	
		UserService uSer = new UserServiceImpl();
		User user = uSer.login("driverA", "123");
		user.setTag("LOGIN");
		ObjectMapper mapper = new ObjectMapper();
		String x="";
			// mapper.writeValue(System.out, user);
			try {
				 x = mapper.writeValueAsString(user);
				System.out.println(x);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
			}
			
			try {
				ObjectMapper readMap = new ObjectMapper();
				User userek = readMap.readValue(x, User.class);
				System.out.println(userek.getEmail());
			
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
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
