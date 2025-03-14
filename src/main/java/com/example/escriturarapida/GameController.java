package com.example.escriturarapida;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    @FXML
    private ProgressBar timerBar;
    @FXML
    private ImageView SunImages;
    @FXML
    private Label timerLabel;
    @FXML
    private Label wordLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private TextField inputField;

    //timer
    private Timeline timeline;
    private int totalTime = 20;
    private int timeLeft;
    private int score = 0;
    private int level = 1;
    private int lives = 4;

    //word list
    private List<String> wordList = new ArrayList<>();
    private String currentWord;
    private final String[] sunImages = {"/com/example/escriturarapida/images/sun1.png",
            "/com/example/escriturarapida/images/sun2.png",
            "/com/example/escriturarapida/images/sun3.png",
            "/com/example/escriturarapida/images/sun4.png"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inputField.setOnAction(event -> checkWord());
        if (SunImages != null) {
            SunImages.setImage(new Image(getClass().getResource(sunImages[0]).toExternalForm()));
        }

        initializeWordList();
        showRandomWord();
        startTimer();


    }

    private void initializeWordList() {
        wordList.add("javafx");
        wordList.add("programar");
        wordList.add("computadora");
        wordList.add("event driven programming");
        wordList.add("programacion orientada a eventos");
        wordList.add("algoritmo");
        wordList.add("arreglos");
        wordList.add("cramps");
        wordList.add("ciclo");
        wordList.add("escritura rapida");
    }

    private void showRandomWord() {
        if (wordList.isEmpty()) {
            wordLabel.setText("No hay palabras");
            return;
        }
        Random random = new Random();
        currentWord = wordList.get(random.nextInt(wordList.size()));
        wordLabel.setText(currentWord);
    }

    public void setTimerDuration(int seconds) {
        this.totalTime = seconds;
        this.timeLeft = seconds;
    }

    private void startTimer() {
        timeLeft = totalTime;
        timerLabel.setText("TIMER: " + timeLeft);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimerUI()));
        timeline.setCycleCount(totalTime);
        timeline.play();
    }

    private void updateTimerUI() {
        timeLeft--;
        timerLabel.setText("TIMER: " + timeLeft);
        timerBar.setProgress((double) timeLeft / totalTime);

        if (timeLeft <= 0) {
            timeLeft = 0;
            timeline.stop();
            loseLife();
            startTimer();
        }
    }

    @FXML
    void OnActionSumbitWord(ActionEvent event) {
        checkWord();
    }

    @FXML
    void OnActionSumbitWordUsingEnter(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            checkWord();
        }
    }

    private void checkWord() {
        String inputText = inputField.getText().trim();

        if (inputText.equalsIgnoreCase(currentWord)) {
            score += 5;
            level++;
            scoreLabel.setText("SCORE: " + score);
            levelLabel.setText("LEVEL: " + level);
            System.out.println("Nivel actual: " + level);

            //if true add 5s to the timer
            timeLeft +=5;
            timeline.stop();
            startTimer();
            showRandomWord();

            //if false lose life and -3 to the timer
        } else {
            loseLife();
            timeLeft-=3;

            if (score > 0) {  // ensures to remove 2 points only if the score is > 0
                score -= 2;}

            scoreLabel.setText("SCORE: " + score);

        }

        inputField.clear(); //clears the input field
    }

    private void loseLife() {
        if (lives > 0) {
            lives--;
            updateLifeImage();
        }   //removes 1 life and switches the image (calls the function)
            //if and only if lives >0

        if (lives <= 0) {
            System.out.println("Game Over");
            timeline.stop();
            wordLabel.setText("GAME OVER");
            inputField.setDisable(true);
        }   //
    }

    private void updateLifeImage() {
        if (lives > 0 && lives <= 4) {
            SunImages.setImage(new Image(getClass().getResource(sunImages[4 - lives]).toExternalForm()));
        }
    }

    public void setImage(Image image) {
        if (SunImages != null) {
            SunImages.setImage(image);

        }
    }

}
