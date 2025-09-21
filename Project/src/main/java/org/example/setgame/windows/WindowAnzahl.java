package org.example.setgame.windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.setgame.HelloApplication;

public class WindowAnzahl {

    private Stage stage;
    private Button confirmButton;
    private Label label;
    private TextField inputField;
    private Button cancelButton;
    private CheckBox longGameCheckBox;

    private BorderPane mainPane;
    private HBox buttonPane;
    private GridPane centerPane;

    private Scene scene;

    private boolean longGame = false;
    private int count = 1;

    public WindowAnzahl(Stage stage) {
        this.stage = stage;

        confirmButton = new Button("OK");
        confirmButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px;");

        cancelButton = new Button("Abbrechen");
        cancelButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-padding: 10px;");

        label = new Label("Mit wie vielen Spielern wollen Sie spielen? (1-6)");
        label.setFont(new Font("Arial", 16));

        inputField = new TextField();

        longGameCheckBox = new CheckBox("Langes Spiel");

        mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: rgba(192,185,185,0.4);"); // Setzen des hellroten Hintergrunds
        buttonPane = new HBox(10);
        centerPane = new GridPane();

        centerPane.setPadding(new Insets(20));
        centerPane.setHgap(10);
        centerPane.setVgap(10);
        centerPane.setAlignment(Pos.CENTER);
        centerPane.add(label, 0, 0, 2, 1);
        centerPane.add(inputField, 0, 1, 2, 1);
        centerPane.add(longGameCheckBox, 0, 2, 2, 1);

        buttonPane.setPadding(new Insets(20));
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(cancelButton, confirmButton);

        mainPane.setCenter(centerPane);
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

        inputField.setOnAction(e -> numberChanged());
        cancelButton.setOnAction(e -> cancel());
        confirmButton.setOnAction(e -> confirm());
        longGameCheckBox.setOnAction(e -> longGame = longGameCheckBox.isSelected());

        scene = new Scene(stackPane, 600, 200); // Größe entsprechend angepasst
        stage.setScene(scene);
        stage.setTitle("Spieler");
        stage.show();
    }

    private void numberChanged() {
        try {
            count = Integer.parseInt(inputField.getText());
            if (!(count < 7 && count > 0)) {
                label.setStyle("-fx-text-fill: red;");
                count = 0;
            } else {
                label.setStyle("-fx-text-fill: black;");
            }
        } catch (NumberFormatException e) {
            label.setStyle("-fx-text-fill: red;");
            count = 0;
        }
    }

    private void cancel() {
        stage.close();
        HelloApplication.getWindowStart().getStage().show();
    }

    private void confirm() {
        numberChanged();
        if (count == 0) {
            label.setStyle("-fx-text-fill: red;");
            return;
        }
        stage.close();
        HelloApplication.setWindowPlayerName(new WindowPlayerName(new Stage(), count, longGame));
    }
}