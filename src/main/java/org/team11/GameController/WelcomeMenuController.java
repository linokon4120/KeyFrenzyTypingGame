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

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WelcomeMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelWelcome;

    @FXML
    private Button buttonStartGame;

    @FXML
    private TextField textFieldUserName;

    /**
     * Once the user hits the Start Game button, we take them to the KeyFrenzyView
     */
    @FXML
    protected void onStartButtonClick() {

        // Retrieve the username from the text field
        String userName = textFieldUserName.getText().trim();

        if (!userName.isEmpty()) {
            // Create an instance of KeyFrenzyView
            KeyFrenzyView theView = new KeyFrenzyView(userName);

            Scene scene = new Scene(theView.getRoot());
//         Attach a CSS file for styling our app
            scene.getStylesheets().add(
                    getClass().getResource("/KeyFrenzy.css")
                            .toExternalForm());
            //this.theController = new KeyFrenzyController(this.theModel, this.theView);

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
