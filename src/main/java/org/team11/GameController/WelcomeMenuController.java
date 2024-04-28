/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/16/2024
 * Time: 8:51 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameController
 * Class: WelcomeMenuController
 *
 * Description:
 *
 * **************************************
 */
package org.team11.GameController;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import java.util.Objects;

public class WelcomeMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button buttonStartGame;

    @FXML
    private TextField textFieldUserName;

    /**
     * Once the user hits the Start Game button, we take them to the KeyFrenzyGameController
     */
    @FXML
    protected void onStartButtonClick() {

        // Retrieve the username from the text field
        String userName = textFieldUserName.getText().trim();

        if (!userName.isEmpty()) {
            // Create an instance of KeyFrenzyGameController
            KeyFrenzyGameController theView = new KeyFrenzyGameController(userName);

            Scene scene = new Scene(theView.getRoot());
            // Attach a CSS file for styling our app
            scene.getStylesheets().add(
                    getClass().getResource("/KeyFrenzy.css")
                            .toExternalForm());

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Key Frenzy Typing Game");
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show();

            // Close the current WelcomeMenu window
            Stage currentStage = (Stage) buttonStartGame.getScene().getWindow();
            currentStage.close();
        }
    }

    @FXML
    void initialize() {
        assert buttonStartGame != null : "fx:id=\"buttonStartGame\" was not injected: check your FXML file 'welcomeMenu.fxml'.";
        assert textFieldUserName != null : "fx:id=\"textFieldUserName\" was not injected: check your FXML file 'welcomeMenu.fxml'.";

    }

}
