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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class GameOverController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Text textGameOver;


    @FXML
    private Button buttonPlayAgain;

    @FXML
    private Button buttonQuitGame;
    @FXML
    protected void onRestartButtonClick() throws IOException {
        // When the start button is clicked, go to the game view
        SceneSwitch.change(textGameOver, "startGameMenu.fxml", 1000, 800, "Typer [In Game]");
    }

    @FXML
    void initialize() {
        assert buttonPlayAgain != null : "fx:id=\"buttonPlayAgain\" was not injected: check your FXML file 'gameOverView.fxml'.";
        assert buttonQuitGame != null : "fx:id=\"buttonQuitGame\" was not injected: check your FXML file 'gameOverView.fxml'.";

    }

}
