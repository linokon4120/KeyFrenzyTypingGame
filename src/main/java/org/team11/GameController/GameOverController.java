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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.team11.TypingMechanism.WordDictionary;

public class GameOverController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private VBox rootGameOver;

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
    private KeyFrenzyGameController gameController;

    public GameOverController () {
        this.rootGameOver = new VBox();
    }

    @FXML
    protected void onRestartButtonClick() {
        try {
            Stage primaryStage = new Stage();
            // Load the FXML file. Obtain the root of the scene graph
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/welcomeMenu.fxml")); // TODO this is only the start menu
            Parent root = loader.load();

            // Set up the stage and show it
            primaryStage.setTitle("Hello FXML!");
            primaryStage.setScene(new Scene(root));
            primaryStage.sizeToScene();
            primaryStage.show();


            // Close the current (game over) stage
            Stage currentStage = (Stage) (buttonPlayAgain).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace(); // Handle loading error
        }

    }

    @FXML
    private void onQuitGameButtonClick() {
        // Close the application
        Platform.exit();
    }

    @FXML
    void initialize() {
        assert buttonPlayAgain != null : "fx:id=\"buttonPlayAgain\" was not injected: check your FXML file 'gameOverView.fxml'.";
        assert buttonQuitGame != null : "fx:id=\"buttonQuitGame\" was not injected: check your FXML file 'gameOverView.fxml'.";
        assert rootGameOver != null : "fx:id=\"rootGameOver\" was not injected: check your FXML file 'gameOverView.fxml'.";
        assert textGameOver != null : "fx:id=\"textGameOver\" was not injected: check your FXML file 'gameOverView.fxml'.";
    }

//    public void transferData(int score) {
//        // Set the game to an instance variable
//        this.dict = dict;
//
//        // Get the score from the game
//        scoreText.setText("Score: " + score);
//
//        // Enable the "restart" button after 1 second
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(() -> buttonPlayAgain.setDisable(false));
//            }
//        }, 1000);

    public void transferData(int score) {
        // Set the game to an instance variable
        this.gameController = gameController;

        // Get the score from the game
        scoreText.setText("Score: " + score);

        // Enable the "restart" button after 1 second
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> buttonPlayAgain.setDisable(false));
            }
        }, 1000);
    }

    public VBox getRootGameOver() {
        return rootGameOver;
    }
}
