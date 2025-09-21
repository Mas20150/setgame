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
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.setgame.HelloApplication;

@Getter
@Setter
public class WindowStart {

    private Scene scene;
    private Button startButton;
    private Label titleLabel;
    private Button ladenButton;
    private Button statistikButton;
    private Button einstellungenButton;
    private Button beendenButton;
    private Stage stage;

    public WindowStart(Stage stage) {
        this.stage = stage;

        titleLabel = new Label("SetGame");
        titleLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 80));
        titleLabel.setStyle("-fx-text-fill: white;");

        startButton = createStyledButton("Start");
        ladenButton = createStyledButton("Laden");
        statistikButton = createStyledButton("Statistik");
        einstellungenButton = createStyledButton("Einstellungen");
        beendenButton = createStyledButton("Beenden");

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.getChildren().addAll(titleLabel, startButton, ladenButton, statistikButton, /*einstellungenButton,*/ beendenButton);

        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(700, 700);

        // Hintergrundbild setzen und unscharf machen
        Image image = new Image("img/jack-hamilton-9SewS6lowEU-unsplash.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(700);
        imageView.setFitHeight(700);
        imageView.setPreserveRatio(false);

        ColorAdjust colorAdjust = new ColorAdjust();
        GaussianBlur blur = new GaussianBlur(25);
        colorAdjust.setInput(blur);
        imageView.setEffect(colorAdjust);

        stackPane.getChildren().addAll(imageView, vbox);
        StackPane.setAlignment(vbox, Pos.CENTER);

        scene = new Scene(stackPane, 700, 700);
        scene.getStylesheets().add("styles.css");

        stage.setTitle("SetGame");
        stage.setScene(scene);
        stage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(300);
        button.setStyle("-fx-font-size: 20px; -fx-background-color: rgba(230,0,8,0.75); -fx-text-fill: white;");
        button.setOnAction(event -> {
            switch (text) {
                case "Start":
                    onClickStartButton();
                    break;
                case "Laden":
                    onClickLadenButton();
                    break;
                case "Statistik":
                    onClickStatistikButton();
                    break;
                case "Einstellungen":
                    onClickEinstellungenButton();
                    break;
                case "Beenden":
                    onClickBeendenButton();
                    break;
            }
        });
        return button;
    }

    private void onClickStartButton() {
        stage.close();
        HelloApplication.setWindowAnzahl(new WindowAnzahl(new Stage()));
    }

    private void onClickLadenButton() {
        stage.close();
        if (HelloApplication.getWindowLaden() == null) {
            HelloApplication.setWindowLaden(new WindowLaden(new Stage()));
        }
        HelloApplication.getWindowLaden().getStage().show();
    }

    private void onClickStatistikButton() {
        stage.close();
        if (HelloApplication.getWindowStatistik() == null) {
            HelloApplication.setWindowStatistik(new WindowStatistik(new Stage()));
        } else {
            HelloApplication.getWindowStatistik().getStage().show();
        }
    }

    private void onClickEinstellungenButton() {
        stage.close();
        if (HelloApplication.getWindowEinstellungen() == null) {
            HelloApplication.setWindowEinstellungen(new WindowEinstellungen(new Stage()));
        } else {
            HelloApplication.getWindowEinstellungen().getStage().show();
        }
    }

    private void onClickBeendenButton() {
        HelloApplication.getWindowStart().getStage().show();
        stage.close();
    }
}