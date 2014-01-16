package Courier.CourierService.Models;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import CourierService.hibernate.HibernateUtil;

public class UserDAO extends GenericDAO<User, Integer> {
	
	public enum UserType {
	    Kierownik, Kierowca;
	}

	public User findByName(String name, String password) {
		Session hibernateSession = this.getSession();
		User person = null;
		Transaction tx = hibernateSession.beginTransaction();
        String sql = "SELECT u FROM User u WHERE u.login = :name AND u.password = :password";
        Query query = hibernateSession.createQuery(sql).setParameter("name", name).setParameter("password", password);
        person = findOne(query);
        tx.commit();
        return person;
    }
	
	public List<User> findByType(UserType type)
	{
		String typeOfUser = type.toString();
		Session hibernateSession = this.getSession();
		List<User> persons = null;
		Transaction tx = hibernateSession.beginTransaction();
        String sql = "SELECT u FROM User u WHERE u.type = :kierowca";       
        Query query = hibernateSession.createQuery(sql).setParameter("kierowca", typeOfUser);
        persons = findMany(query);
        tx.commit();
		return persons;
	}
	
	public void save(User user)
	{
		Session hibernateSession = this.getSession();
		Transaction tx = hibernateSession.beginTransaction();
        hibernateSession.saveOrUpdate(user);
        tx.commit();
	}
	
}
