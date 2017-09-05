package project.dao.repository;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import project.utils.HibernateSessionFactory;

/**
 * Created by Elvira on 15.04.2017.
 */
public class AbstractImpl {

    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
   @Autowired
    protected AbstractImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return getSessionFactory().openSession();
    }

    protected AbstractImpl() {
    }

}


