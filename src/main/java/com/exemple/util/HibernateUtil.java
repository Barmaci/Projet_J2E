package com.exemple.util;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static Session getSession() {
        Configuration cf = new Configuration();
        SessionFactory sessionFactory = cf.configure().buildSessionFactory();

        return sessionFactory.openSession();
    }

    public static<T> void initialize(T o){
        Hibernate.initialize(o);
    }
}
