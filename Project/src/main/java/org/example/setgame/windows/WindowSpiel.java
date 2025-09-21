package org.example.setgame.windows;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import lombok.Getter;
import lombok.Setter;
import org.example.setgame.HelloApplication;
import org.example.setgame.ent.CardEnt;
import org.example.setgame.ent.GameBoardCardEnt;
import org.example.setgame.ent.PlayerEnt;
import org.example.setgame.rep.CardRepository;

import java.net.URL;
import java.util.*;


@Getter
@Setter
//Julius
public class WindowSpiel {

    DoubleProperty sceneWidth = new SimpleDoubleProperty();
    DoubleProperty sceneHeight = new SimpleDoubleProperty();
    Timer timer = new Timer("Timer");
    private BorderPane mainPane;
    private Stage stage;
    private BorderPane bottomPane;
    private BorderPane topPane;
    private GridPane gameBoardPane;
    private Button saveButton;
    private Button menuButton;
    private Label scoreLabel;
    private Label turnPlayerLabel;
    private Button settingsButton;
    private HashMap<CardEnt, ImageView> cardImages = new HashMap<>();
    private ArrayList<Button> cardButtons = new ArrayList<>();
    private Date timerTime;
    private Button newCardsButton;
    private boolean isPlayerOnTurn = false;
    private int turnPlayer;
    private HashMap<String,Integer> selectedCards = new HashMap<>();
    private int playerCount;
    private Screen screen = Screen.getPrimary();
    private Label keyLabel;

    public WindowSpiel(int count, Stage stage){



        this.stage = stage;
        this.playerCount = count;

        mainPane = new BorderPane();
        bottomPane = new BorderPane();
        topPane = new BorderPane();
        saveButton = new Button("Speichern");
        menuButton = new Button("Hauptmenu");
        scoreLabel = new Label("Spielstand: ");
        turnPlayerLabel = new Label("Zugspieler: ");
        gameBoardPane = new GridPane();
        settingsButton = new Button("Einstellungen");
        newCardsButton = new Button("Neue Karten");

        keyLabel = new Label();

        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());



        settingsButton.setOnAction(e->onSettings());
        saveButton.setOnAction(e->onSave());
        menuButton.setOnAction(e->onMenu());
        newCardsButton.setOnAction(e->onNewCards());

        topPane.setPadding(new Insets(10, 10, 10, 10));
        topPane.setLeft(saveButton);
        topPane.setRight(menuButton);
        topPane.setCenter(newCardsButton);

        bottomPane.setPadding(new Insets(10, 10, 10, 10));
        bottomPane.setLeft(scoreLabel);
        bottomPane.setCenter(turnPlayerLabel);
        //bottomPane.setRight(settingsButton);
        bottomPane.setRight(keyLabel);

        gameBoardPane.setHgap(10);
        gameBoardPane.setVgap(10);




        for (int i = 0; i<4; i++){
            for (int j = 0; j<3; j++){

                Button button = new Button();
                ImageView imageView = new ImageView("/img/A - Cards/s0000.png");
                button.setGraphic(imageView);
                int finalI = i;
                int finalJ = j;
                button.setOnAction(e -> cardSelected(finalI*3 + finalJ));
                int temp = finalI*3 + finalJ;
                button.setText(temp+"");
                cardButtons.add(button);

                gameBoardPane.add(cardButtons.get(cardButtons.size()-1),j,i);

            }
        }

        RowConstraints rowConstraints = new RowConstraints();
        gameBoardPane.getRowConstraints().add(rowConstraints);

        mainPane.setPadding(new Insets(30, 30, 30, 30));
        mainPane.setTop(topPane);
        mainPane.setBottom(bottomPane);
        mainPane.setCenter(gameBoardPane);

        Scene scene = new Scene(mainPane);
        scene.heightProperty().add(stage.heightProperty());
        scene.widthProperty().add(stage.widthProperty());

        stage.setScene(scene);
        stage.setTitle("Spiel");
        stage.show();

        stage.getScene().setOnKeyPressed(this::keyPressed);
    }

    private void onNewCards() {

        if (cardButtons.size() == 12) {

            for (int i = 0; i < 3; i++) {
                Button button = new Button();
                int finalI = i;
                button.setOnAction(e -> cardSelected(12 + finalI));
                cardButtons.add(button);
                gameBoardPane.add(cardButtons.get(cardButtons.size() - 1), i, 4);
            }
            HelloApplication.getGameEnt().noSetFound();
            refreshImage();
        }


    }

    public void refreshImage(){

        StringBuilder sb = new StringBuilder();

        ArrayList<PlayerEnt> playerTemp = new ArrayList<>(HelloApplication.getGameEnt().getPlayers());


        for (int i = 0; i< playerTemp.size(); i++){

            char x = '\u0041';
            x += (char) i;
            sb.append(playerTemp.get(i).getName()).append(": ").append(x).append("\n");
        }

        keyLabel.setText(sb.toString());

        StringBuilder score = new StringBuilder();
        for (PlayerEnt playerEnt: HelloApplication.getGameEnt().getPlayers()){
            score.append(playerEnt.getName()).append(": ").append(playerEnt.getSets().size() + playerEnt.getScoresfromformerround()).append("\n");
        }

        scoreLabel.setText(score.toString());

        for (GameBoardCardEnt gameBoardCardEnt: HelloApplication.getGameEnt().getGameboardCardSet()){

            if (gameBoardCardEnt.getCard() == null){
                cardButtons.get(gameBoardCardEnt.getPosition()).setGraphic(null);
                cardButtons.get(gameBoardCardEnt.getPosition()).onActionProperty().setValue(null);
                return;
            }

            ImageView imageView = new ImageView("img/A - Cards/" + gameBoardCardEnt.getCard().getUrl() + ".png");
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(100);

            cardButtons.get(gameBoardCardEnt.getPosition()).setGraphic(imageView);
            cardButtons.get(gameBoardCardEnt.getPosition()).setShape(new Button().getShape());

        }
        if (HelloApplication.getGameEnt().getGameboardCardSet().size() <= 12){
            if (cardButtons.size() > 12) {
                cardButtons.subList(12, cardButtons.size()).clear();
            }
            if (gameBoardPane.getChildren().size()>12)
                gameBoardPane.getChildren().remove(12,15);

        }
    }

    public void onMenu() {
        stage.close();
        HelloApplication.getWindowStart().getStage().show();

    }

    public void onSave(){
        HelloApplication.getGameEnt().saveGame();

    }

    public void onSettings(){
        stage.close();
        new WindowEinstellungen(new Stage());
    }

    public void keyPressed(KeyEvent e){

        ArrayList<Integer> playerIds = new ArrayList<>();

        for (PlayerEnt player: HelloApplication.getGameEnt().getPlayers()){
            playerIds.add(player.getId());
        }
        if (playerIds.size()<6){
            for(int i = playerIds.size(); i<6; i++){
                playerIds.add(-1);
            }
        }

        switch (e.getText()){
            case "a": selectCard(playerIds.get(0));
            case "b": selectCard(playerIds.get(1));
            case "c": selectCard(playerIds.get(2));
            case "d": selectCard(playerIds.get(3));
            case "e": selectCard(playerIds.get(4));
            case "f": selectCard(playerIds.get(5));
        }
    }

    public void selectCard(int playerId){
        if (!isPlayerOnTurn && playerId != -1){
            String playerName = "";
            for (PlayerEnt player: HelloApplication.getGameEnt().getPlayers()){
                if (player.getId() == playerId){
                    playerName = player.getName();
                }
            }

            turnPlayerLabel.setText(playerName);
            isPlayerOnTurn = true;
            turnPlayer = playerId;
            startTimer();
        }
    }

    private void startTimer() {

        timer = new Timer();
        long delay = 1000L;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (isPlayerOnTurn) {
                        isPlayerOnTurn = false;
                        refreshShapes();
                    }
                });
            }
        },10000);
    }

    public void cardSelected(int pos){

        if (isPlayerOnTurn){
            if (gameBoardPane.getChildren().get(pos) instanceof Button) {
                ((Button) gameBoardPane.getChildren().get(pos)).setShape(new Rectangle());
                ((Button) gameBoardPane.getChildren().get(pos)).getShape().setStroke(Color.GREEN);
                ((Button) gameBoardPane.getChildren().get(pos)).getShape().setStrokeWidth(30);
            }

            if (selectedCards.containsKey("0") && selectedCards.containsKey("1") && selectedCards.containsKey("2")){
                HashMap<String, Integer> temp = new HashMap<>(selectedCards);
                selectedCards.clear();
                HelloApplication.getGameEnt().selectSet(temp.get("0"),temp.get("1"),temp.get("2"),turnPlayer);

            }
            else if (!selectedCards.containsKey("0")){
                selectedCards.put("0",pos);
            }
            else if (!selectedCards.containsKey("1")){
                selectedCards.put("1",pos);
            }
            else if (!selectedCards.containsKey("2")){
                selectedCards.put("2",pos);
                HashMap<String, Integer> temp = new HashMap<>(selectedCards);
                selectedCards.clear();
                HelloApplication.getGameEnt().selectSet(temp.get("0"),temp.get("1"),temp.get("2"),turnPlayer);

                setPlayerOnTurn(false);
                refreshShapes();
                timer.cancel();
            }
        }
        else {
            selectedCards.clear();
        }
    }

    public void refreshShapes(){
        for (Button button: cardButtons){
            button.setShape(new Button().getShape());
        }
        getTurnPlayerLabel().setText("Zugspieler");

    }

}
