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
import java.net.URL;
import java.util.ResourceBundle;


public class HelloController {
    @FXML
    private ImageView SunImages;

    @FXML
    public void initialize() {
        // Cargar la imagen cuando la escena se inicializa
        SunImages.setImage(new Image(getClass().getResource("/com/example/escriturarapida/images/sun1.png").toExternalForm()));

    }


    @FXML
    void onActionStartGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapida/game.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del juego
            GameController gameController = loader.getController();

            // Establecer la imagen inicial en el GameController
            gameController.setImage(new Image(getClass().getResource("/com/example/escriturarapida/images/sun1.png").toExternalForm()));

            // Cambiar a la escena del juego
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
