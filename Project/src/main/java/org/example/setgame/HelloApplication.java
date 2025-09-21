package org.example.setgame;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.setgame.ent.*;
import org.example.setgame.ent.enums.CardColor;
import org.example.setgame.ent.enums.CardNumber;
import org.example.setgame.ent.enums.CardShape;
import org.example.setgame.ent.enums.CardSymbol;
import org.example.setgame.rep.CardRepository;
import org.example.setgame.rep.GameRepository;
import org.example.setgame.rep.PlayerRepository;
import org.example.setgame.windows.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;

public class HelloApplication extends Application {

    @Getter
    private static List<CardEnt> allCards;

    @Getter
    @Setter
    private static WindowStart windowStart;

    @Getter
    @Setter
    private static WindowSpiel windowSpiel;

    @Getter
    @Setter
    private static GameEnt gameEnt;

    @Getter
    @Setter
    private static WindowPlayerName windowPlayerName;

    @Getter
    @Setter
    private static WindowEinstellungen windowEinstellungen;

    @Getter
    @Setter
    private static WindowAnzahl windowAnzahl;

    @Getter
    @Setter
    private static WindowStatistik windowStatistik;

    @Getter
    @Setter
    private static WindowSpeichern windowSpeichern;

    @Getter
    @Setter
    private static WindowLaden windowLaden;

    @Getter
    @Setter
    private static boolean gameRuns = false;

    @Getter
    private static final CardRepository cardRepository = new CardRepository();

    @Getter
    private static final GameRepository gameRepository = new GameRepository();

    @Getter
    private static final PlayerRepository playerRepository = new PlayerRepository();

    //provisorisch
    @Getter
    private static Color backgroundColor = Color.GRAY;

    public static void setBackgroundColor(Color backgroundColor) {
        HelloApplication.backgroundColor = backgroundColor;
    }

    @Override
    public void start(Stage stage) throws IOException {
        windowStart = new WindowStart(stage);
    }

    public static void main(String[] args) {
        createAllCards();
        Application.launch(args);
    }

    private static void createAllCards() {
        allCards = cardRepository.loadAll();
    }

    public static void createCardUrl(){
        ArrayList<CardEnt> cards = new ArrayList<>();
        CardRepository cardRepository = new CardRepository();

        for (CardSymbol cardSymbol : CardSymbol.values()){
            for (CardColor cardColor : CardColor.values()){
                for (CardShape cardShape : CardShape.values()){
                    for (CardNumber cardNumber : CardNumber.values()){
                        cards.add(new CardEnt(cardColor, cardSymbol, cardNumber, cardShape));
                    }
                }
            }
        }

        HashMap<String, Integer> map = new HashMap<>();
        map.put("diamond", 0);
        map.put("squiggle", 1);
        map.put("oval", 2);
        map.put("red", 0);
        map.put("green", 1);
        map.put("purple", 2);
        map.put("solid", 0);
        map.put("striped", 1);
        map.put("outlined", 2);
        map.put("one", 0);
        map.put("two", 1);
        map.put("three", 2);

        for (CardEnt card : cards) {
            String url = "s";
            String symbol = map.get(card.getSymbol().toString()).toString();
            String color = map.get(card.getColor().toString()).toString();
            String shape = map.get(card.getShading().toString()).toString();
            String number = map.get(card.getNumber().toString()).toString();

            url = url + number + shape + color + symbol;
            card.setUrl(url);
            getCardRepository().insert(card);
        }
    }
}