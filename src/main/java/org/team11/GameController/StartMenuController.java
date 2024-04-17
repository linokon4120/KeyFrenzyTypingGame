/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/16/2024
 * Time: 8:51 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameController
 * Class: StartMenuController
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StartMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelWelcome;

    @FXML
    private Button buttonStartGame;

    @FXML
    private TextField textFieldUserName;


    @FXML
    protected void onStartButtonClick() throws IOException {


        SceneSwitch.change(labelWelcome, "KeyFrenzyView", 1000, 800, "Typer [In Game]");
    }

    @FXML
    void initialize() {
        assert buttonStartGame != null : "fx:id=\"buttonStartGame\" was not injected: check your FXML file 'startGameMenu.fxml'.";
        assert textFieldUserName != null : "fx:id=\"textFieldUserName\" was not injected: check your FXML file 'startGameMenu.fxml'.";

    }

}
