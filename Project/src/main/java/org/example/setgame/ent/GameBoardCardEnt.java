package org.example.setgame.ent;

//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "gameboardcard")
public class GameBoardCardEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameboardcard_id_gen")
    @SequenceGenerator(name = "gameboardcard_id_gen", sequenceName = "gameboardcard_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "gameid", referencedColumnName = "id")
    private GameEnt game;

    @Column(name = "position")
    private int position;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "cardid", referencedColumnName = "id")
    private CardEnt card;

    @Transient
    private boolean toRemove = false;

    public GameBoardCardEnt(GameEnt game, int position, CardEnt card) {
        this.game = game;
        this.position = position;
        this.card = card;
    }
    public GameBoardCardEnt(GameEnt game, int position) {
        this.game = game;
        this.position = position;
    }

}