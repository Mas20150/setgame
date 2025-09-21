package org.example.setgame.ent;

//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player")
public class PlayerEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_id_gen")
    @SequenceGenerator(name = "player_id_gen", sequenceName = "player_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumn(name = "gameid",referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private GameEnt game;

    //@Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "scoresfromformerround")
    private int scoresfromformerround = 0;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player", cascade = CascadeType.MERGE)
    //@JoinColumn(name = "player", table = "set")
    private Set<SetEnt> sets = new HashSet<>();


    public PlayerEnt(GameEnt gameEnt, String name) {

        this.game = gameEnt;
        this.name = name;
    }
}