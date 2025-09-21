package org.example.setgame.windows;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.setgame.HelloApplication;
import org.example.setgame.ent.GameEnt;
import org.example.setgame.ent.PlayerEnt;
import org.example.setgame.ent.enums.GameModus;
import org.example.setgame.rep.GameRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@Getter
@Setter
public class WindowStatistik {

        private final AnchorPane root;
        private Label label1;
        private Label label2;
        private Label label3;
        private Label label4;
        private Label label5;
        private Label label6;
        private Label label7;
        private Label label8;
        private Label label9;
        private Label label10;
        private Label label11;
        private Label label12;
        private PieChart pieChart;
        private Scene scene;
        private ListView listView1;
        private ListView listView2;
        private Button button;
        private Stage stage;

        private SessionFactory factory;

        public WindowStatistik(Stage primaryStage) {
                this.stage = primaryStage;
                factory = new Configuration().configure("hibernate.cfg.xml")
                        .addAnnotatedClass(GameEnt.class)
                        .buildSessionFactory();

                root = new AnchorPane();

                label1 = new Label("Statistik");
                label1.setFont(Font.font("System Bold", 30.0));
                label1.setLayoutX(250.0);

                label2 = new Label("persönliche Statistik:");
                label2.setLayoutX(38.0);
                label2.setLayoutY(70.0);
                label2.setAlignment(Pos.CENTER_RIGHT);

                label3 = new Label("gesamte Spiele:");
                label3.setLayoutX(38.0);
                label3.setLayoutY(101.0);
                label3.setAlignment(Pos.CENTER_RIGHT);

                label4 = new Label("gewinne Spiele:");
                label4.setLayoutX(38.0);
                label4.setLayoutY(132.0);
                label4.setAlignment(Pos.CENTER_RIGHT);

                label5 = new Label("verlorene Spiele:");
                label5.setLayoutX(38.0);
                label5.setLayoutY(162.0);
                label5.setAlignment(Pos.CENTER_RIGHT);

                label6 = new Label("unentschiedene Spiele:");
                label6.setLayoutX(38.0);
                label6.setLayoutY(192.0);
                label6.setAlignment(Pos.CENTER_RIGHT);

                label7 = new Label();
                label7.setLayoutX(200);
                label7.setLayoutY(69.0);

                label8 = new Label();
                label8.setLayoutX(200);
                label8.setLayoutY(100.0);

                label9 = new Label();
                label9.setLayoutX(200);
                label9.setLayoutY(131);

                label10 = new Label();
                label10.setLayoutX(200);
                label10.setLayoutY(161.0);

                label11 = new Label();
                label11.setLayoutX(200);
                label11.setLayoutY(191.0);

                // Fetch data from the database
                fetchDataFromDatabase();

                pieChart.setLayoutX(361.0);
                pieChart.setLayoutY(46.0);
                pieChart.setPrefSize(145.0, 165.0);

                button = new Button("Abbrechen");
                button.setOnAction(e -> back());

                button.setLayoutX(14.0);
                button.setLayoutY(500.0);
                button.setMnemonicParsing(false);

                Line line = new Line();
                line.setLayoutX(108.0);
                line.setLayoutY(250.0);
                line.setStartX(-108.0);
                line.setEndX(500.0);

                label12 = new Label("Ranking");
                label12.setFont(Font.font("System Bold", 26.0));
                label12.setLayoutX(256.0);
                label12.setLayoutY(280.0);
                label12.setAlignment(Pos.CENTER);

                listView1 = new ListView();
                listView1.setLayoutX(74.0);
                listView1.setLayoutY(340);
                listView1.setPrefSize(200.0, 131.0);

                listView2 = new ListView();
                listView2.setLayoutX(318.0);
                listView2.setLayoutY(340.0);
                listView2.setPrefSize(207.0, 131.0);

                root.getChildren().addAll(label1, label2, label3, label4, label5, label6,
                        label7, label8, label9, label10, label11, label12, button,
                        pieChart, line, listView1, listView2);

                Scene scene = new Scene(root, 600, 480);
                scene.setFill(Color.TRANSPARENT);

                primaryStage.setTitle("Statistik-Anwendung");
                primaryStage.setScene(scene);
                primaryStage.show();
        }

        private void fetchDataFromDatabase() {
                GameRepository gameRepository = new GameRepository();
                try {


                        // Fetch the statistics from the database
                        List<GameEnt> games = gameRepository.loadAll();

                        // Assuming you have methods to get these values from the database
                        int totalGames = games.size();
                        int winGames = (int) games.stream().filter(game -> game.getModus()== GameModus.LONG).count();
                        /*int lostGames = (int) games.stream().filter(game -> !game.isWon()).count();
                        int tiedGames = (int) games.stream().filter(GameEnt::isTied).count();*/

                        label7.setText(String.valueOf(totalGames));  // persönliche Statistik
                        label8.setText(String.valueOf(totalGames));  // gesamte Spiele
                        label9.setText(String.valueOf(winGames));    // gewinne Spiele
                        //label10.setText(String.valueOf(lostGames));  // verlorene Spiele
                        //label11.setText(String.valueOf(tiedGames));  // unentschiedene Spiele

                        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                                new PieChart.Data("gewinn", winGames)
                               // new PieChart.Data("verloren", lostGames),
                                //new PieChart.Data("unentschieden", tiedGames)
                        );

                        pieChart = new PieChart(valueList);
                        pieChart.setTitle("Spielstand");

                        pieChart.getData().forEach(data -> {
                                String percentage = String.format("%.2f%%", (data.getPieValue() / totalGames * 100));
                                Tooltip toolTip = new Tooltip(percentage);
                                Tooltip.install(data.getNode(), toolTip);
                        });


                }finally {

                }
        }

        public void back() {
                stage.close();
                HelloApplication.getWindowStart().getStage().show();
        }
}