package com.example.escriturarapida;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador para la escena de inicio del juego.
 * Maneja la carga de la imagen inicial y la transición a la escena del juego.
 */
public class HelloController {

    @FXML
    private ImageView SunImages;

    /**
     * Metodo de inicialización que se ejecuta cuando se carga la escena.
     * Carga la imagen inicial del sol en la interfaz.
     */
    @FXML
    public void initialize() {
        // Cargar la imagen cuando la escena se inicializa
        SunImages.setImage(new Image(getClass().getResource("/com/example/escriturarapida/images/sun1.png").toExternalForm()));
    }

    /**
     * Maneja el evento de inicio del juego.
     * Carga y muestra la escena del juego cuando el usuario presiona el boton de inicio.
     *
     * @param event Evento de acción del boton de inicio.
     */
    @FXML
    void onActionStartGame(ActionEvent event) {
        try {
            // Cargar la escena del juego desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapida/game.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del juego
            GameController gameController = loader.getController();

            // Establecer la imagen inicial en el GameController
            gameController.setImage(new Image(getClass().getResource("/com/example/escriturarapida/images/sun1.png").toExternalForm()));

            // Obtener la ventana actual y cambiar la escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace(); // Imprime el error en la consola si ocurre un problema al cargar la escena
        }
    }
}
