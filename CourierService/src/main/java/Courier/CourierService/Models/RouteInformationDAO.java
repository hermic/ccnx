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
//		SELECT *
//		FROM `RouteInformation`
//		WHERE `UserId` = (
//		SELECT UserId
//		FROM User
//		WHERE Login = 'driverA' ) 
//        String sql = "SELECT u FROM RouteInformation u WHERE UserId = "
//        		+ "(SELECT usr FROM User WHERE Login = :login)"; 
		String sql = "SELECT u FROM RouteInformation u WHERE UserId = 1";
        Query query = hibernateSession.createQuery(sql);//.setParameter("login", user.getLogin());     
        routes = findMany(query);
        tx.commit();
		return routes;	
	}

	public List<RouteInformation> findByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
