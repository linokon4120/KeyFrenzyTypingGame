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
package org.team11.GameView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.team11.GameModel.KeyFrenzyModel;
import org.team11.GameView.KeyFrenzyView;

import java.io.IOException;

public class GameMain extends Application{
    private KeyFrenzyModel theModel;
    private KeyFrenzyView theView;
  //  private KeyFrenzyController theController;


    public static void main(String[] args) {launch(args);}


    @Override
    public void init() throws Exception {
        super.init();
        this.theModel = new KeyFrenzyModel();
        this.theView = new KeyFrenzyView(this.theModel);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {

//        // Load the FXML file. Obtain the root of the scene graph
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/fxml/startGameMenu.fxml")); // TODO this is only the start menu
//        Parent root = loader.load();
//
//        // Set up the stage and show it
//        primaryStage.setTitle("Hello FXML!");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();

        Scene scene = new Scene(theView.getRoot());
//         Attach a CSS file for styling our app
        scene.getStylesheets().add(
                getClass().getResource("/KeyFrenzy.css")
                        .toExternalForm());
        //this.theController = new KeyFrenzyController(this.theModel, this.theView);

        primaryStage.setTitle("Key Frenzy Typing Game");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();


    }
}
 //TODO: Clock is 15 minutes


