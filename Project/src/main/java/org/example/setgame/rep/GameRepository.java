package org.example.setgame.rep;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.setgame.HibernateConnector;
import org.example.setgame.ent.CardEnt;
import org.example.setgame.ent.GameEnt;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@NoArgsConstructor
public class GameRepository {


    private static final HibernateConnector<GameEnt> hibernateConnector = new HibernateConnector<>(new GameEnt());

    public void insert(GameEnt gameEnt) {
        hibernateConnector.insert(gameEnt);
    }
    public void update(GameEnt gameEnt) {
        hibernateConnector.update(gameEnt);
    }

    public List<GameEnt> loadAll(){
        SessionFactory factory = hibernateConnector.getSessionFactory();

        try (factory; Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<GameEnt> cards = (List<GameEnt>) session.createQuery("from " + new GameEnt().getClass().getSimpleName(), new GameEnt().getClass()).getResultList();
            session.getTransaction().commit();
            return cards;
        }
    }

    public List<GameEnt> loadByFinished(boolean finshed) {

        SessionFactory factory = hibernateConnector.getSessionFactory();

        try (factory; Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<GameEnt> cq = cb.createQuery(GameEnt.class);

            Root<GameEnt> root = cq.from(GameEnt.class);
            cq.select(root).where(cb.equal(root.get("finished"), finshed));
            Query query = session.createQuery(cq);

            return (List<GameEnt>) query.getResultList();

        }
    }



}
