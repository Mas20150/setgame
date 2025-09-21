package org.example.setgame.windows;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.setgame.HelloApplication;

import java.io.File;
import java.util.HashMap;

import static javafx.scene.paint.Color.*;

@Getter
@Setter
public class WindowEinstellungen {
    private ListView<String> colorListView;
    private Slider effectSlider;
    private Slider musicSlider;
    private Label effectLabel;
    private Label musicLabel;
    private Button backButton;
    private ObservableList<String> colorsList;
    private HashMap<String, Color> colorMap = new HashMap<>();
    private GridPane sliderPane;
    private BorderPane mainPane;
    private Scene scene;
    private Stage stage;
    private MediaPlayer mediaPlayer;

    public WindowEinstellungen(Stage stage) {
        this.stage = stage;
        colorListView = new ListView<>();

        colorsList = FXCollections.observableArrayList("GRAY", "BLUE", "WHITE");
        colorMap.put("GRAY", GRAY);
        colorMap.put("BLUE", BLUE);
        colorMap.put("WHITE", WHITE);
        colorListView.setItems(colorsList);
        colorListView.setPrefSize(150, 100);  // Set smaller size for the ListView
        colorListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> changeBackground(newValue));

        effectSlider = new Slider();
        musicSlider = new Slider();
        effectLabel = new Label("Effekte");
        musicLabel = new Label("Musik");

        backButton = new Button("Zurück");
        backButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-padding: 10px;");
        backButton.setOnAction(event -> back());

        sliderPane = new GridPane();
        mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: rgba(192,185,185,0.4);"); // Set light red background

        effectSlider.setMin(0);
        effectSlider.setMax(100);
        effectSlider.setValue(effectSlider.getMax());
        effectSlider.setShowTickLabels(true);
        effectSlider.setMaxSize(300, 20);
        effectSlider.valueProperty().addListener(event -> changeEffect());

        musicSlider.setMin(0);
        musicSlider.setMax(100);
        musicSlider.setValue(musicSlider.getMax());
        musicSlider.setShowTickLabels(true);
        musicSlider.valueProperty().addListener((observable, oldValue, newValue) -> changeMusic(newValue.doubleValue()));

        sliderPane.setPrefSize(300, 50);
        musicSlider.setMaxSize(sliderPane.getPrefWidth(), sliderPane.getPrefHeight());
        sliderPane.add(effectLabel, 0, 0);
        sliderPane.add(effectSlider, 0, 1);
        sliderPane.add(musicLabel, 0, 2);
        sliderPane.add(musicSlider, 0, 3);
        sliderPane.setAlignment(Pos.CENTER);
        sliderPane.setHgap(10);

        HBox buttonPane = new HBox(10);
        buttonPane.setPadding(new Insets(20));
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        buttonPane.getChildren().add(backButton);

        mainPane.setTop(colorListView);
        mainPane.setCenter(sliderPane);
        mainPane.setBottom(buttonPane);
        mainPane.setPadding(new Insets(10, 10, 10, 10));

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

        scene = new Scene(stackPane, 600, 480);
        stage.setScene(scene);
        stage.setTitle("Einstellungen");
        stage.show();

        initializeMusic();
    }

    private void initializeMusic() {
        String musicFile = "img/2020-08-10_-_Go_Beyond_-_David_Fesliyan.mp3"; // Pfad zur Musikdatei
        File file = new File(musicFile);
        if (file.exists()) {
            System.out.println("Musikdatei gefunden: " + musicFile);
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Endlosschleife
            mediaPlayer.play();
            System.out.println("Musik wird abgespielt.");
        } else {
            System.out.println("Musikdatei nicht gefunden: " + musicFile);
        }
    }

    private void changeMusic(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume / 100.0); // Lautstärke einstellen
            System.out.println("Musiklautstärke geändert: " + volume);
        }
    }

    public void changeEffect() {
        // Effekte ändern
    }

    public void changeBackground(String color) {
        Color selectedColor = colorMap.get(color);
        if (selectedColor != null) {
            HelloApplication.setBackgroundColor(selectedColor);
            System.out.println("Hintergrundfarbe geändert zu: " + color);
        }
    }

    public void back() {
        stage.close();
        if (HelloApplication.isGameRuns()) {
            HelloApplication.getWindowSpiel().getStage().show();
        } else {
            HelloApplication.getWindowStart().getStage().show();
        }
    }
}