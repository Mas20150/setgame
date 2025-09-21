package org.example.setgame.windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.setgame.HelloApplication;
import org.example.setgame.ent.GameEnt;

import java.util.ArrayList;

@Getter
@Setter
public class WindowPlayerName {

    private ArrayList<TextField> playerNameFields;
    private Stage stage;
    private Button confirmButton;
    private Button cancelButton;

    private BorderPane borderPane;
    private GridPane textFieldPane;
    private HBox buttonPane;

    private Scene scene;

    public WindowPlayerName(Stage stage, int count, boolean longGame) {
        playerNameFields = new ArrayList<>();
        this.stage = stage;
        stage.setTitle("Spielernamen");

        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: rgba(192,185,185,0.4);"); // Setzen des hellroten Hintergrunds
        textFieldPane = new GridPane();
        buttonPane = new HBox(10);

        textFieldPane.setPadding(new Insets(20));
        textFieldPane.setHgap(10);
        textFieldPane.setVgap(10);
        textFieldPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < count; i++) {
            Label label = new Label("Spieler " + (i + 1) + ":");
            TextField field = new TextField();
            playerNameFields.add(field);

            textFieldPane.add(label, 0, i);
            textFieldPane.add(field, 1, i);
        }

        confirmButton = new Button("Bestätigen");
        confirmButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px;");
        cancelButton = new Button("Abbrechen");
        cancelButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-padding: 10px;");

        buttonPane.setPadding(new Insets(20));
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        buttonPane.getChildren().addAll(cancelButton, confirmButton);

        borderPane.setCenter(textFieldPane);
        borderPane.setBottom(buttonPane);

        // Hintergrundbild setzen und unscharf machen
        Image image = new Image("img/jack-hamilton-9SewS6lowEU-unsplash.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(480);
        imageView.setPreserveRatio(false);

        ColorAdjust colorAdjust = new ColorAdjust();
        GaussianBlur blur = new GaussianBlur(20);
        colorAdjust.setInput(blur);
        imageView.setEffect(colorAdjust);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, borderPane);

        cancelButton.setOnAction(e -> cancel());
        confirmButton.setOnAction(event -> confirm(count, longGame));

        scene = new Scene(stackPane, 600, 200); // Größe entsprechend angepasst
        stage.setScene(scene);
        stage.show();
    }

    public void cancel() {
        stage.close();
        HelloApplication.getWindowStart().getStage().show();
    }

    public void confirm(int count, boolean longGame) {
        ArrayList<String> playerNames = new ArrayList<>();

        for (TextField field : playerNameFields) {
            playerNames.add(field.getText());
        }

        stage.close();
        HelloApplication.setWindowSpiel(new WindowSpiel(count, new Stage()));
        HelloApplication.getWindowSpiel().getStage().show();
        HelloApplication.setGameEnt(new GameEnt());
        HelloApplication.getGameEnt().startNewGame(count, playerNames, longGame);
    }
}