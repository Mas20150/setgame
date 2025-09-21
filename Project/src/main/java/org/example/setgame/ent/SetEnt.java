package org.example.setgame.ent;

//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "set")
public class SetEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "set_id_gen")
    @SequenceGenerator(name = "set_id_gen", sequenceName = "set_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "gameid")
    private GameEnt game;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "player")
    private PlayerEnt player;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "card0")
    private CardEnt card0;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "card1")
    private CardEnt card1;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "card2")
    private CardEnt card2;

    @Column(name = "isvalid")
    private Boolean isvalid;

    @Column(name = "time")
    private Instant time;

    @Column(name = "round")
    private int round;

    private boolean validColor(){
        return (card0.getColor().equals(card1.getColor()) && card0.getColor().equals(card2.getColor()))
                || (!card0.getColor().equals(card1.getColor())
                    && !card0.getColor().equals(card2.getColor())
                    && !card1.getColor().equals(card2.getColor()));
    }

    private boolean validNumber(){
        return (card0.getNumber() .equals( card1.getNumber()) && card0.getNumber().equals(card2.getNumber()))
                ||(!card0.getNumber().equals( card1.getNumber())
                    && !card0.getNumber().equals(card2.getNumber())
                    && !card1.getNumber().equals(card2.getNumber()));
    }

    private boolean validShading(){
        return (card0.getShading().equals(card1.getShading()) && card0.getShading().equals(card2.getShading()))
                || (!card0.getShading().equals(card1.getShading())
                    && !card0.getShading().equals(card2.getShading())
                    && !card1.getShading().equals(card2.getShading()));
    }

    private boolean validSymbol(){
        return (card0.getSymbol().equals(card1.getSymbol()) && card0.getSymbol().equals(card2.getSymbol()))
                || (!card0.getSymbol().equals(card1.getSymbol())
                    && !card0.getSymbol().equals(card2.getSymbol())
                    && !card1.getSymbol().equals(card2.getSymbol()));
    }

    public boolean isValid(){
        isvalid = validColor() && validNumber() && validShading() && validSymbol();
        System.out.println(validColor());
        System.out.println(validNumber());
        System.out.println(validShading());
        System.out.println(validSymbol());
        return isvalid;
    }

    public SetEnt(CardEnt card0, CardEnt card1, CardEnt card2, PlayerEnt player, GameEnt game, int round){
        this.card0 = card0;
        this.card1 = card1;
        this.card2 = card2;
        this.game = game;
        this.player = player;
        this.round = round;

        isValid();


    }


}