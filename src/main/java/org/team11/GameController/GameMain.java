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
 *
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
    private KeyFrenzyGameController theView;

    public static void main(String[] args) {launch(args);}


    @Override
    public void init() throws Exception {
        super.init();

    }
    @Override
    public void start(Stage primaryStage) throws IOException {

        // Load the FXML file. Obtain the root of the scene graph
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/welcomeMenu.fxml"));
        Parent root = loader.load();

        // Set up the stage and show it
        primaryStage.setTitle("Hello FXML!");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();


    }
}



