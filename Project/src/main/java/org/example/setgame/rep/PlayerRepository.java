package org.example.setgame.rep;

import lombok.Getter;
import lombok.Setter;
import org.example.setgame.HibernateConnector;
import org.example.setgame.ent.PlayerEnt;

public class PlayerRepository {


    private static final HibernateConnector<PlayerEnt> hibConnector = new HibernateConnector<>(new PlayerEnt());

    private void update(PlayerEnt playerEnt) {
        hibConnector.update(playerEnt);
    }

    public void insert(PlayerEnt playerEnt) {
        hibConnector.insert(playerEnt);
    }
}
