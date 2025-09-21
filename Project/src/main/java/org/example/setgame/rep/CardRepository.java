package org.example.setgame.rep;

import org.example.setgame.HibernateConnector;
import org.example.setgame.ent.CardEnt;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CardRepository {

    private static final HibernateConnector<CardEnt> connector = new HibernateConnector<>(new CardEnt());

    public List<CardEnt> loadAll() {
        SessionFactory factory = connector.getSessionFactory();

        try (factory; Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<CardEnt> cards = (List<CardEnt>) session.createQuery("from " + new CardEnt().getClass().getSimpleName(), new CardEnt().getClass()).getResultList();
            session.getTransaction().commit();
            return cards;
        }
    }

    public void updateCard(CardEnt card) {
        connector.update(card);
    }

    public void insert(CardEnt card) {
        connector.insert(card);
    }

}
