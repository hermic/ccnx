package Courier.CourierService.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import CourierService.hibernate.HibernateUtil;

public abstract class GenericDAO<T, ID extends Serializable>
{	 
    protected Session getSession() {
        return HibernateUtil.getSession();
    }
 
    public void save(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.saveOrUpdate(entity);
    }
 
    public void merge(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.merge(entity);
    }
 
    public void delete(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }
 
    public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.list();
        return t;
    }
 
    public T findOne(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }
 
    public T findByID(Class clas, Integer id) {
        Session hibernateSession = this.getSession();
        T t = null;
        Transaction tx = hibernateSession.beginTransaction();
        t = (T) hibernateSession.get(clas, id);
        tx.commit();
        return t;
    }
 
    public List findAll(Class clazz) {
        Session hibernateSession = this.getSession();
        List T = null;
        Transaction tx = hibernateSession.beginTransaction();
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        tx.commit();
        T = query.list();
        return T;
    }
}
