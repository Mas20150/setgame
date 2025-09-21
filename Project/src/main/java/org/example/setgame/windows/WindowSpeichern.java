package org.example.setgame.windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.setgame.HelloApplication;

public class WindowSpeichern {
    private final Stage stage;
    private final Scene scene;
    private final BorderPane mainPane;
    private final Label label;
    private final Button speichernButton;
    private final Button loeschenButton;

    public WindowSpeichern(Stage stage) {
        this.stage = stage;

        stage.setTitle("Speichern");

        // Erstellen der Labels und Buttons
        label = new Label("Speichern");
        label.setFont(Font.font("System Bold", 31));
        label.setTextFill(Color.BLACK);

        speichernButton = new Button("Speichern");
        speichernButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px;");
        speichernButton.setOnAction(e -> save());

        loeschenButton = new Button("Abbrechen");
        loeschenButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-padding: 10px;");
        loeschenButton.setOnAction(e -> cancel());

        // Layout für die Buttons
        HBox buttonPane = new HBox(10, speichernButton, loeschenButton);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(20, 0, 0, 0));

        // Layout für den Label
        HBox labelPane = new HBox(label);
        labelPane.setAlignment(Pos.CENTER);
        labelPane.setPadding(new Insets(20, 0, 0, 0));

        // Haupt-Layout
        mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: rgba(192,185,185,0.4);"); // Set light red background
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.setTop(labelPane);
        mainPane.setCenter(buttonPane);

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
        stackPane.getChildren().addAll(imageView, mainPane);

        // Erstellen der Szene
        scene = new Scene(stackPane, 600, 200);

        // Anzeigen der Szene
        stage.setScene(scene);
        stage.show();
    }

    private void save() {
        stage.close();
        HelloApplication.getWindowStart().getStage().show();
        HelloApplication.getGameEnt().saveGame();
    }

    private void cancel() {
        stage.close();
        HelloApplication.getWindowStart().getStage().show();
    }
}