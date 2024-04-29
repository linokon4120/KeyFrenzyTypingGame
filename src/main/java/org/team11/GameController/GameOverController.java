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
 * Description: This is the controller class
 * for the game over fxml
 *
 * **************************************
 */
package org.team11.GameController;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverController {


    @FXML
    private VBox rootGameOver;

    //Text that corresponds with the action: game over
    @FXML
    private Text textGameOver;

    //Play again button
    @FXML
    private Button buttonPlayAgain;

    //Quit game button
    @FXML
    private Button buttonQuitGame;


    /**
     * A constructor for the GameOverController class
     */
    public GameOverController () {
        this.rootGameOver = new VBox();
    }

    /**
     * The restart button implementation
     */
    @FXML
    public void onRestartButtonClick() {
        try {
            Stage primaryStage = new Stage();
            // Load the FXML file. Obtain the root of the scene graph
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/welcomeMenu.fxml"));
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

    /**
     * The button that quits the game
     */
    @FXML
    public void onQuitGameButtonClick() {
        // Close the application
        Platform.exit();
    }

    /**
     * Initialises all the buttons
     */
    @FXML
    void initialize() {
        assert buttonPlayAgain != null : "fx:id=\"buttonPlayAgain\" was not injected: check your FXML file 'gameOverView.fxml'.";
        assert buttonQuitGame != null : "fx:id=\"buttonQuitGame\" was not injected: check your FXML file 'gameOverView.fxml'.";
        assert rootGameOver != null : "fx:id=\"rootGameOver\" was not injected: check your FXML file 'gameOverView.fxml'.";
        assert textGameOver != null : "fx:id=\"textGameOver\" was not injected: check your FXML file 'gameOverView.fxml'.";
    }
}
