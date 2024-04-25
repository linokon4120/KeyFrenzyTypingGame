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
import java.util.Objects;

public class SceneSwitch {

    // Reference to the game controller
    private static KeyFrenzyGameController theView;

    /**
     * Manage the transitions between different scenes
     */
    private SceneSwitch() {
//        this.theView = new KeyFrenzyGameController();
    }

    /**
     * Switches the scene to the game over scene
     * @param node, The JavaFX Node from which the scene switch is initiated.
     * @param page The path to the FXML file of the game over screen.
     * @return The controller associated with the game over screen.
     * @throws IOException if an I/O error occurs.
     */
    public static <T> T changeToGameOver(Node node, String page) throws IOException {
        return change(node, page, 320, 240, "Typer");
    }


    /**
     * Generic method to switch the scene to any specified page with customizable width, height, and title.
     * @param node The JavaFX Node from which the scene switch is initiated.
     * @param page The path to the FXML file of the target screen.
     * @param width of scene
     * @param height of scene
     * @param title of stage
     * @return The controller associated with the target screen.
     * @throws IOException if there's an I/O error
     */
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

    /**
     * Switches the scene to the main game screen.
     * @param node The JavaFX Node from which the scene switch is initiated.
     * @param page The path to the FXML file of the main game screen.
     * @param width of screen
     * @param height of screen
     * @param title of stage
     * @return the controller associated with the main game screen.
     * @throws IOException if an I/O occurs
     */
    public <T> T changeToGame(Node node, String page, int width, int height, String title) throws IOException {
        Stage primaryStage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GameMain.class.getResource(page));
                Scene scene = new Scene(theView.getRoot());

        // Attach CSS to style the game view
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/KeyFrenzy.css"))
                        .toExternalForm());
        //this.theController = new KeyFrenzyController(this.theModel, this.theView);

        primaryStage.setTitle("Key Frenzy Typing Game");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        return fxmlLoader.getController();
    }

}