package org.example.setgame;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

public class HibernateConnector<K> {

    private final K k;

    public HibernateConnector(K k) {
        this.k = k;
    }


    public SessionFactory getSessionFactory() {
        SessionFactory factory = new Configuration()
                .configure(hibernateConfigFile).addAnnotatedClass(k.getClass())
                .buildSessionFactory();
        return factory;
    }

    private final String hibernateConfigFile = "hibernate.cfg.xml";

    public <T> K read(K k, T id) {

        SessionFactory factory = new Configuration()
                .configure(hibernateConfigFile).addAnnotatedClass(k.getClass())
                .buildSessionFactory();

        try (factory; Session session = factory.getCurrentSession()) {

            session.beginTransaction();


            Object o = session.get(k.getClass(), id);
            k = (K) o;
            session.getTransaction().commit();

            return k;
        }
    }

    public void insert(Object o){

        SessionFactory factory = new Configuration()
                .configure(hibernateConfigFile).addAnnotatedClass(o.getClass())
                .buildSessionFactory();

        try (factory; Session session = factory.getCurrentSession()) {

            session.beginTransaction();
            session.persist(o);
            session.getTransaction().commit();
        }

    }

    public void update(Object o){
        SessionFactory factory = new Configuration().
                configure(hibernateConfigFile).addAnnotatedClass(o.getClass()).
                buildSessionFactory();

        try (factory; Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.merge(o);
            session.getTransaction().commit();
        }
    }

    public void write(Object o){

        SessionFactory factory = new Configuration()
                .configure(hibernateConfigFile).addAnnotatedClass(o.getClass())
                .buildSessionFactory();

        try (factory; Session session = factory.getCurrentSession()) {

            session.beginTransaction();
            session.persist(o);
            session.getTransaction().commit();
        }
    }
}
