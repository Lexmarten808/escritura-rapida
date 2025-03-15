package com.example.escriturarapida;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

/**
 * Controlador del juego de escritura rapida.
 * Maneja la logica del juego, incluyendo el temporizador, puntuacion, nivel y vidas.
 *
 */
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

    private Timeline timeline; // Temporizador del juego
    private int totalTime = 20;
    private int timeLeft;
    private int score = 0;
    private int level = 1;
    private int lives = 4;

    private List<String> wordList = new ArrayList<>(); // Lista de palabras
    private String currentWord; // Palabra actual en pantalla

    private final String[] sunImages = {
            "/com/example/escriturarapida/images/sun1.png",
            "/com/example/escriturarapida/images/sun2.png",
            "/com/example/escriturarapida/images/sun3.png",
            "/com/example/escriturarapida/images/sun4.png"
    };

    /**
     * Inicializa el juego configurando eventos, imagenes y temporizador.
     */
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

    /**
     * Llena la lista de palabras para el juego.
     */
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

    /**
     * Muestra una palabra aleatoria en pantalla.
     */
    private void showRandomWord() {
        if (wordList.isEmpty()) {
            wordLabel.setText("No hay palabras");
            return;
        }
        Random random = new Random();
        currentWord = wordList.get(random.nextInt(wordList.size()));
        wordLabel.setText(currentWord);
    }

    /**
     * Configura la duracion del temporizador.
     *
     * @param seconds Duracion en segundos.
     */
    public void setTimerDuration(int seconds) {
        this.totalTime = seconds;
        this.timeLeft = seconds;
    }

    /**
     * Inicia el temporizador del juego.
     */
    private void startTimer() {
        timeLeft = totalTime;
        timerLabel.setText("TIMER: " + timeLeft);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimerUI()));
        timeline.setCycleCount(totalTime);
        timeline.play();
    }

    /**
     * Actualiza la interfaz del temporizador.
     */
    private void updateTimerUI() {
        timeLeft--;
        timerLabel.setText("TIMER: " + timeLeft);
        timerBar.setProgress((double) timeLeft / totalTime);

        if (timeLeft <= 0) {
            timeline.stop();
            loseLife();
            startTimer();
        }
    }

    /**
     * Metodo asociado a un boton para enviar la palabra ingresada.
     */
    @FXML
    void OnActionSumbitWord(ActionEvent event) {
        checkWord();
    }

    /**
     * Metodo para detectar la tecla Enter y enviar la palabra ingresada.
     */
    @FXML
    void OnActionSumbitWordUsingEnter(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            checkWord();
        }
    }

    /**
     * Verifica si la palabra ingresada es correcta.
     * Si es correcta, aumenta el puntaje y el tiempo.
     * Si es incorrecta, resta vidas y tiempo.
     */
    private void checkWord() {
        String inputText = inputField.getText().trim();

        if (inputText.equalsIgnoreCase(currentWord)) {
            score += 5;
            level++;
            scoreLabel.setText("SCORE: " + score);
            levelLabel.setText("LEVEL: " + level);

            timeLeft += 5;
            timeline.stop();
            startTimer();
            showRandomWord();
        } else {
            loseLife();
            timeLeft -= 3;
            if (score > 0) {
                score -= 2;
            }
            scoreLabel.setText("SCORE: " + score);
        }
        inputField.clear();
    }

    /**
     * Reduce una vida al jugador y actualiza la imagen.
     * Si las vidas llegan a 0, finaliza el juego.
     */
    private void loseLife() {
        if (lives > 0) {
            lives--;
            updateLifeImage();
        }
        if (lives <= 0) {
            System.out.println("Game Over");
            timeline.stop();
            wordLabel.setText("GAME OVER");
            inputField.setDisable(true);
        }
    }

    /**
     * Actualiza la imagen del sol segun la cantidad de vidas restantes.
     */
    private void updateLifeImage() {
        if (lives > 0 && lives <= 4) {
            SunImages.setImage(new Image(getClass().getResource(sunImages[4 - lives]).toExternalForm()));
        }
    }

    /**
     * Establece una imagen en el componente de imagen del sol.
     *
     * @param image Imagen a mostrar.
     */
    public void setImage(Image image) {
        if (SunImages != null) {
            SunImages.setImage(image);
        }
    }
}
