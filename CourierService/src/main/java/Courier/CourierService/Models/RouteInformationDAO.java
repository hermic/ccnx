package Courier.CourierService.Models;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Courier.CourierService.Models.UserDAO.UserType;

public class RouteInformationDAO extends GenericDAO<RouteInformation, Integer>{

	public List<RouteInformation> findAll()
	{
		Session hibernateSession = this.getSession();
		List<RouteInformation> routes = null;
		Transaction tx = hibernateSession.beginTransaction();
        String sql = "SELECT u FROM RouteInformation u";       
        Query query = hibernateSession.createQuery(sql);       
        routes = findMany(query);
        tx.commit();
		return routes;	
	}
	
	public void save(RouteInformation route)
	{
		Session hibernateSession = this.getSession();
		Transaction tx = hibernateSession.beginTransaction();
        hibernateSession.saveOrUpdate(route);
        tx.commit();
	}

	public List<RouteInformation> findByUser(User user) {
		Session hibernateSession = this.getSession();
		List<RouteInformation> routes = null;
		Transaction tx = hibernateSession.beginTransaction();
		String sql = "from RouteInformation table1 where table1.user in (select table2.userId from User table2 where login = :login)";
        Query query = hibernateSession.createQuery(sql).setParameter("login", user.getLogin());     
        routes = findMany(query);
        tx.commit();
		return routes;	
	}

	public List<RouteInformation> findByUserId(Integer userId) {
		Session hibernateSession = this.getSession();
		List<RouteInformation> routes = null;
		Transaction tx = hibernateSession.beginTransaction();
		String sql = "from RouteInformation table1 where table1.user in (select table2.userId from User table2 where userId = :id)";
        Query query = hibernateSession.createQuery(sql).setParameter("id", userId);     
        routes = findMany(query);
        tx.commit();
		return routes;	
	}
}
