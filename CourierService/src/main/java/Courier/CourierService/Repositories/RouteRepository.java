package Courier.CourierService.Repositories;

import java.util.List;

import Courier.CourierService.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Courier.CourierService.HibernateUtil;
import CourierModels.Route;

public class RouteRepository {

	public List<Route> Get()
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
	    Session session = sf.openSession();
	 
	    List<Route> routes = session.createQuery("from Route").list();
	    session.close();
	    return routes;
	}
}
