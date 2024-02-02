package com.example.movidlegame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class Game extends Application {
    List<Film> filmList = ReadsData.ReadingData();
    Film randomFilm = RandomFilm();
    Label lblTitle,lblYear,lblGenre,lblOrigin,lblDirect,lblStar,lblReal;
    TextField txt;
    Button btn;
    Film guessedFilm;
    int guessCount = 0;
    final int maxGuess = 5;
    VBox vbox;
    HBox hbox;

    @Override
    public void start(Stage stage) throws IOException {

        txt = new TextField();
        btn = new Button("Guess!");
        lblReal = new Label();

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.getChildren().addAll(txt,btn);
        hBox.setAlignment(Pos.TOP_CENTER);

        hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);

        vbox = new VBox();
        vbox.getChildren().add(hbox);

        btn.setOnAction(event->{
            gameProcess();
            if (guessCount >= maxGuess) {
                btn.setDisable(true);
            }
            txt.clear();
        });

        BorderPane root = new BorderPane();
        root.setTop(hBox);
        root.setCenter(vbox);
        root.setBottom(lblReal);

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Movidle");
        stage.setScene(scene);
        stage.show();
    }
    public Film RandomFilm() {
        ReadsData movieReader = new ReadsData();
        Random random = new Random();
        int index = random.nextInt(filmList.size());
        Film film = filmList.get(index);
        return film;
    }
    public void gameProcess(){
        lblReal.setText(randomFilm.getTitle());
        String text = txt.getText().trim();

        if (text.isEmpty() || !isFilmintheList(text)) {
            lblReal.setText("Enter a valid film,please!");
            lblReal.setFont(Font.font(15));
            return;
        }

        for (Film film : filmList) {
            if (film.getTitle().equalsIgnoreCase(text))
                guessedFilm = film;
        }

        changeLabels(guessedFilm);
        HBox guessPane = new HBox();
        guessPane.setPadding(new Insets(10));
        guessPane.setSpacing(10);
        guessPane.getChildren().addAll(lblTitle, lblYear, lblGenre, lblOrigin, lblDirect, lblStar);
        vbox.getChildren().add(guessPane);

        controlGuess();
        guessCount++;
    }
    public void controlGuess(){
        if(randomFilm.getTitle().equals(guessedFilm.getTitle()))
            lblTitle.setStyle("-fx-background-color: green");
        else
            lblTitle.setStyle("-fx-background-color: red");
        if(String.valueOf(randomFilm.getYear()).equals(String.valueOf(guessedFilm.getYear())))
            lblYear.setStyle("-fx-background-color: green");
        else
            lblYear.setStyle("-fx-background-color: red");
        if(randomFilm.getGenre().equals(guessedFilm.getGenre()))
            lblGenre.setStyle("-fx-background-color: green");
        else
            lblGenre.setStyle("-fx-background-color: red");
        if(randomFilm.getOrigin().equals(guessedFilm.getOrigin()))
            lblOrigin.setStyle("-fx-background-color: green");
        else
            lblOrigin.setStyle("-fx-background-color: red");
        if(randomFilm.getDirector().equals(guessedFilm.getDirector()))
            lblDirect.setStyle("-fx-background-color: green");
        else
            lblDirect.setStyle("-fx-background-color: red");
        if(randomFilm.getStar().equals(guessedFilm.getStar()))
            lblStar.setStyle("-fx-background-color: green");
        else
            lblStar.setStyle("-fx-background-color: red");

        if (randomFilm.getTitle().equals(guessedFilm.getTitle())
                && String.valueOf(randomFilm.getYear()).equals(String.valueOf(guessedFilm.getYear()))
                && randomFilm.getGenre().equals(guessedFilm.getGenre())
                && randomFilm.getOrigin().equals(guessedFilm.getOrigin())
                && randomFilm.getDirector().equals(guessedFilm.getDirector())
                && randomFilm.getStar().equals(guessedFilm.getStar()))
            winGame();
    }
    public void winGame() {
        Stage primarystage = new Stage();
        primarystage.setTitle("You Win!!");

        Button btnStart = new Button("Restart");
        btnStart.setOnAction(event -> {
            primarystage.close();
            resetGame();
        });

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(btnStart);

        Scene scene = new Scene(hBox, 300, 100);
        primarystage.setScene(scene);
        primarystage.show();
    }
    public void resetGame() {
        vbox.getChildren().clear();
        lblTitle.setText("");
        lblGenre.setText("");
        lblOrigin.setText("");
        lblYear.setText("");
        lblStar.setText("");
        lblDirect.setText("");
        lblReal.setText("");
        txt.clear();
        randomFilm = RandomFilm();
        guessCount = 0;
    }
    public void changeLabels(Film movie) {
        lblTitle = new Label(movie.getTitle());
        lblYear = new Label(String.valueOf(movie.getYear()));
        lblGenre = new Label(movie.getGenre());
        lblOrigin = new Label(movie.getOrigin());
        lblDirect = new Label(movie.getDirector());
        lblStar = new Label(movie.getStar());
    }
    public boolean isFilmintheList(String title) {
        for (Film film : filmList) {
            if (film.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }
}



