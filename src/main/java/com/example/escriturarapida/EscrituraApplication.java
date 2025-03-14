package com.example.escriturarapida;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class EscrituraApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/escriturarapida/menu.fxml")); // Load menu.fxml

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/escriturarapida/images/iconV2.png")));

        primaryStage.setTitle("Escritura Rápida");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
