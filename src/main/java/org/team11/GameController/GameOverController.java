/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/16/2024
 * Time: 9:35 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameController
 * Class: GameOverController
 *
 * Description:
 *
 * **************************************
 */
package org.team11.GameController;

import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.team11.GameView.WordDictionary;

public class GameOverController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Text textGameOver;

    @FXML
    public Text scoreText;


    @FXML
    private Button buttonPlayAgain;

    @FXML
    private Button buttonQuitGame;

    @FXML
    private WordDictionary dict;

    @FXML
    protected void onRestartButtonClick() throws IOException {
        // When the start button is clicked, go to the game view
        SceneSwitch.change(textGameOver, "welcomeMenu.fxml", 1000, 800, "Typer [In Game]");
    }

    @FXML
    void initialize() {
        assert buttonPlayAgain != null : "fx:id=\"buttonPlayAgain\" was not injected: check your FXML file 'gameOverView.fxml'.";
        assert buttonQuitGame != null : "fx:id=\"buttonQuitGame\" was not injected: check your FXML file 'gameOverView.fxml'.";

    }

    protected void transferData(WordDictionary dict) {
        // Set the game to an instance variable
        this.dict = dict;

        // Get the score from the game
        scoreText.setText("Score: " + dict.getScore());

        // Enable the "restart" button after 1 second
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> buttonPlayAgain.setDisable(false));
            }
        }, 1000);
    }

}
