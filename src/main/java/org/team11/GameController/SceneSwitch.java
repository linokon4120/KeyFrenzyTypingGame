/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/16/2024
 * Time: 9:03 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameController
 * Class: SceneSwitch
 *
 * Description:
 *
 * **************************************
 */
package org.team11.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitch {

    private static KeyFrenzyGameController theView;

    private SceneSwitch() {
//        this.theView = new KeyFrenzyGameController();
    }

    public static <T> T changeToGameOver(Node node, String page) throws IOException {
        return change(node, page, 320, 240, "Typer");
    }

//    public static <T> T change(Node node, String page, int width, int height) throws IOException {
//        return change(node, page, width, height, "Typer");
//    }

    public static <T> T change(Node node, String page, int width, int height, String title) throws IOException {
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GameMain.class.getResource(page));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        return fxmlLoader.getController();
    }

    public <T> T changeToGame(Node node, String page, int width, int height, String title) throws IOException {
        Stage primaryStage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GameMain.class.getResource(page));
                Scene scene = new Scene(theView.getRoot());

        // Attach CSS to style the game view
        scene.getStylesheets().add(
                getClass().getResource("/KeyFrenzy.css")
                        .toExternalForm());
        //this.theController = new KeyFrenzyController(this.theModel, this.theView);

        primaryStage.setTitle("Key Frenzy Typing Game");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        return fxmlLoader.getController();
    }

}