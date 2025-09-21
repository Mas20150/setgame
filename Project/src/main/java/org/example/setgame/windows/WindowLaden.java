package org.example.setgame.windows;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;
import org.example.setgame.HelloApplication;
import org.example.setgame.ent.GameEnt;
import org.hibernate.mapping.Column;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class WindowLaden {
    private AnchorPane root;
    private Label label;
    private Slider slider;
    private DatePicker datePicker;
    private TableView<GameEnt> tableView;
    private Button loadButton;
    private Button deleteButton;
    private Button backButton;
    private Stage stage;
    private Column idColumn;
    private Column countColumn;
    private Column dateColumn;



    public WindowLaden(Stage primaryStage) {
        this.stage = primaryStage;

        root = new AnchorPane();
        root.setPrefSize(600, 480);

        label = new Label("Spielstand Wählen");
        label.setFont(new Font("System Bold", 30.0));
        label.setStyle("-fx-text-fill: white;");

        datePicker = new DatePicker();

        slider = new Slider();
        slider.setOrientation(javafx.geometry.Orientation.VERTICAL);

        List<GameEnt> gameEntSet = HelloApplication.getGameRepository().loadByFinished(false);
        ObservableList<GameEnt> gameEntObservableList = FXCollections.observableList(gameEntSet);


        tableView = new TableView<>(gameEntObservableList);

        TableColumn<GameEnt, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getId()));
        TableColumn<GameEnt, Integer> countCol = new TableColumn<>("Spieleranzahl");
        countCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getCountOfPlayers()));
        TableColumn<GameEnt, Date> dateCol = new TableColumn<>("Datum");
        dateCol.setCellValueFactory(p-> new SimpleObjectProperty<>(p.getValue().getLastSaved()));

        tableView.getColumns().setAll(idCol, countCol, dateCol);
        tableView.setItems(gameEntObservableList);

        loadButton = createStyledButton("Laden");
        deleteButton = createStyledButton("Löschen");
        backButton = createStyledButton("Abbrechen");
        backButton.setOnAction(e -> back());
        loadButton.setOnAction(e -> load());

        VBox vbox = new VBox(20, label, datePicker, tableView, loadButton, deleteButton, backButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: rgba(192,185,185,0.4); -fx-background-radius: 10;"); // Light red with opacity

        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(600, 480);

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

        stackPane.getChildren().addAll(imageView, vbox);
        StackPane.setAlignment(vbox, Pos.CENTER);

        Scene scene = new Scene(stackPane);
        scene.getStylesheets().add("styles.css");

        primaryStage.setTitle("Spielstand");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 16px; -fx-background-color: rgba(230,0,8,0.75); -fx-text-fill: white;");
        return button;
    }

    private void load() {
        GameEnt gameEnt = tableView.getSelectionModel().getSelectedItem();
        HelloApplication.setGameEnt(gameEnt);
        HelloApplication.setWindowSpiel(new WindowSpiel(gameEnt.getCountOfPlayers(),new Stage()));
        gameEnt.loadGame();
    }

    public void back() {
        stage.close();
        HelloApplication.getWindowStart().getStage().show();
    }
}