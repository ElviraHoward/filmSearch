package project.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * Created by Elvira on 12.03.2017.
 */
public class HibernateSessionFactory {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

