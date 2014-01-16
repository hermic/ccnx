package Courier.CourierService.Models;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RouteDAO extends GenericDAO<Route, Integer>{

	public List<Route> findAll()
	{
		Session hibernateSession = this.getSession();
		List<Route> routes = null;
		Transaction tx = hibernateSession.beginTransaction();
        String sql = "SELECT u FROM Route u";       
        Query query = hibernateSession.createQuery(sql);       
        routes = findMany(query);
        tx.commit();
		return routes;	
	}
	
	public void save(Route route)
	{
		Session hibernateSession = this.getSession();
		Transaction tx = hibernateSession.beginTransaction();
        hibernateSession.saveOrUpdate(route);
        tx.commit();
	}
}
