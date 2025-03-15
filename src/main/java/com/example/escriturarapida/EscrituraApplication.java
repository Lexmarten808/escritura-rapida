package com.example.escriturarapida;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Aplicacion principal del juego de escritura rapida.
 * Carga la interfaz de usuario y configura la ventana principal.
 *
 * @author Brandon Lasprilla Aristizabal
 * @version 1.0
 */
public class EscrituraApplication extends Application {

    /**
     * Inicia la aplicacion cargando la escena principal desde el archivo FXML.
     *
     * @param primaryStage La ventana principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/escriturarapida/menu.fxml")); // Load menu.fxml

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/escriturarapida/images/iconV2.png")));

        primaryStage.setTitle("Escritura Rapida");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Metodo principal que lanza la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}

