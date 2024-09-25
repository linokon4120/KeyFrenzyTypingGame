/* ***************************************
 *
 * Scrum Master: Ellyn Ngo
 * Product Owner: Hannah Tran
 * Developer: Holiness Kerandi, Rahul Sibal
 * Project: Key Frenzy
 * Package: org.team11.GameController
 * Class: WelcomeMenuController
 *
 * Description:
 * This controller class manages user interactions on the Welcome Menu screen of the Key Frenzy Typing Game.
 * It handles actions such as starting the game and retrieving the user's username from a text field.
 * Upon clicking the Start Game button,
 * it initiates the game and opens the main game screen while closing the current Welcome Menu window."
 * **************************************
 */

package org.team11.GameController;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WelcomeMenuController {

    @FXML
    public Button buttonStartGame;

    @FXML
    public TextField textFieldUserName;

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

    /**
     * Initialises the start game button and text field username
     */
    @FXML
    void initialize() {
        assert buttonStartGame != null : "fx:id=\"buttonStartGame\" was not injected: check your FXML file 'welcomeMenu.fxml'.";
        assert textFieldUserName != null : "fx:id=\"textFieldUserName\" was not injected: check your FXML file 'welcomeMenu.fxml'.";

    }

    /**
     * @return the start game button
     */
    public Button getButtonStartGame() {
        return buttonStartGame;
    }

    /**
     * @return the username text field
     */
    public TextField getTextFieldUserName() {
        return textFieldUserName;
    }
}
