package org.example.setgame.ent;

//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.setgame.ent.enums.CardColor;
import org.example.setgame.ent.enums.CardNumber;
import org.example.setgame.ent.enums.CardShape;
import org.example.setgame.ent.enums.CardSymbol;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card")
public class CardEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_id_gen")
    @SequenceGenerator(name = "card_id_gen", sequenceName = "cards_card_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", length = Integer.MAX_VALUE)
    private CardColor color;

    @Enumerated(EnumType.STRING)
    @Column(name = "symbol", length = Integer.MAX_VALUE)
    private CardSymbol symbol;

    @Enumerated(EnumType.STRING)
    @Column(name = "number")
    private CardNumber number;

    @Enumerated(EnumType.STRING)
    @Column(name = "shading", length = Integer.MAX_VALUE)
    private CardShape shading;

    @Column(name = "url")
    private String url;

    public CardEnt(CardColor color, CardSymbol symbol, CardNumber number, CardShape shading) {
        this.color = color;
        this.symbol = symbol;
        this.number = number;
        this.shading = shading;
    }

    @Override
    public boolean equals(Object o) {

        if (getClass() != o.getClass())
            return false;

        return color.equals(((CardEnt) o).color)
                && symbol.equals(((CardEnt) o).symbol)
                && number.equals(((CardEnt) o).number)
                && shading.equals(((CardEnt) o).shading);
    }


}