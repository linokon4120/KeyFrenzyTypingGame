/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Holiness Kerandi
 * Section: 02
 * Date: 4/11/24
 * Time: 11:20 AM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameModel
 * Class: MainCharacter
 *
 * Description:
 *
 * ****************************************
 */
package org.team11.GameModel;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class MainCharacter {
    private Circle node;
    private String name;
    private GridPane gridPane;
    private Label timeSpentLabel;
    private Label userNicknameLabel;
    private TextField userTypeBox;

    /**
     * Creates the main character
     */
    public MainCharacter(String name, GridPane gridPane, Label timeSpentLabel, Label userNicknameLabel) {

//        node = new Circle(20);
//        node.setFill(Color.RED);
        this.name = name;
        this.gridPane = gridPane;
        this.timeSpentLabel = timeSpentLabel;
        this.userNicknameLabel = userNicknameLabel;
//        setListeners();

    }

    public Circle getNode(){
        return node;
    }

    //TODO :Fix the position even after the ghosts appear and disappear
    /**
     * Sets the position of the main Character
     * @param centerX x co-ordinate of the main character
     * @param centerY y co-ordinate of the main character
     */

    public void setPosition(double centerX, double centerY){
        // Calculate the position relative to the top-left corner of the game pane
        double x = centerX - node.getRadius();
        double y = centerY - node.getRadius();

        // Set the position of the main character
        node.setLayoutX(x);
        node.setLayoutY(y);
    }

//    private void setListeners() {
//        setOnMouseClicked(event -> {
//            Label nicknameLabel = new Label(name);
//            nicknameLabel.getStyleClass().add("nickname-label");
//            nicknameLabel.setLayoutX(getLayoutX() - nicknameLabel.getPrefWidth() / 2 + getWidth() / 2);
//            nicknameLabel.setLayoutY(getLayoutY() - nicknameLabel.getPrefHeight());
//            gridPane.getChildren().add(nicknameLabel);
//        });
//    }

    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
            // Handle user input
            // For example:
            String userInput = userTypeBox.getText().trim();
            userTypeBox.clear();
        }
    }

}