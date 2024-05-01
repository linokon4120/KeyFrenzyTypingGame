/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/8/2024
 * Time: 2:22 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameView
 * Class: GameMain
 *
 * Description:
 * GameMain class serves as the starting point for the KeyFrenzy game application,
 * initializing the graphical user interface (GUI) and displaying the welcome menu to the user.
 * **************************************
 */
package org.team11.GameController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameMain extends Application{

    public static void main(String[] args) {launch(args);}


    @Override
    public void init() throws Exception {
        super.init();

    }

    /**
     * Sets up the primary stage by loading the FXML file containing the welcome menu layout,
     * configuring the stage with the loaded scene, and displaying the stage.
     * @param primaryStage , sets up and defines the appearance of the initial scene
     * @throws IOException input exception
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        // Load the FXML file. Obtain the root of the scene graph
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/welcomeMenu.fxml"));
        Parent root = loader.load();

        // Set up the stage and show it
        primaryStage.setTitle("KeyFrenzy Welcome Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}

