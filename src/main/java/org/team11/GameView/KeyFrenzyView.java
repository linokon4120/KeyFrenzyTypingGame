/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/8/2024
 * Time: 2:35 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameView
 * Class: KeyFrenzyView
 *
 * Description:
 *
 * **************************************
 */
package org.team11.GameView;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import org.team11.GameModel.Ghost;
import org.team11.GameModel.KeyFrenzyModel;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KeyFrenzyView {
    private final KeyFrenzyModel theModel;
//    private Stage primaryStage;

    private VBox root;
    private FlowPane topPane;
    private Label labelMessageBanner;
    private Label currentScore;
    private VBox gamePane;
    private List<Ghost> ghosts;
    private Circle mainCharacter;


    public KeyFrenzyView(KeyFrenzyModel theModel) {
        this.theModel = theModel;

        initSceneGraph();
    }

    public void initSceneGraph() {
        // Initialize the root pane
        this.root = new VBox();



        // Create and configure the game pane
        gamePane = new VBox();
        this.gamePane.getStyleClass().add("game-pane"); // Apply CSS class to gamePane


        // Create and configure the message banner
        labelMessageBanner = new Label("Type words on ghosts to destroy them!");
        currentScore = new Label("Current Score: ");
        this.gamePane.getChildren().add(currentScore);


        // Initialize ghosts
        this.ghosts = new ArrayList<>();
        initializeGhosts();

        // Create and position the main character (circle)
        mainCharacter = new Circle(20); // Radius of the circle
        mainCharacter.setFill(Color.RED); // Set fill color
        mainCharacter.getStyleClass().add("main-character");

        mainCharacter.setLayoutX(300 - 20); // Adjust for circle radius
        mainCharacter.setLayoutY(500 - 20); // Adjust for circle radius

        // Add the main character to the gamePane
        gamePane.getChildren().add(mainCharacter);
//        gamePane.getChildren().addAll(ghosts);


        this.root.getChildren().add(labelMessageBanner);
        this.root.getChildren().add(gamePane);

    }



    private void initializeGhosts() {
        String[] words = {"Ghost", "Spooky", "Boo", "Haunt"};


        double paneWidth = gamePane.getPrefWidth();
        double paneHeight = gamePane.getPrefHeight();

        // Create and position four ghosts with words
        for (int i = 0; i < 4; i++) {
            double x = 0;
            double y = 0;

            switch (i) {
                case 0: // Top border center
                    x = paneWidth / 2;
                    y = 50; // Adjust y position
                    break;
                case 1: // Bottom border center
                    x = paneWidth / 2;
                    y = paneHeight - 50; // Adjust y position
                    break;
                case 2: // Left border center
                    x = 50; // Adjust x position
                    y = paneHeight / 2;
                    break;
                case 3: // Right border center
                    x = paneWidth - 50; // Adjust x position
                    y = paneHeight / 2;
                    break;
            }

            Ghost ghost = new Ghost(words[i]);
            ghost.getNode().getStyleClass().add("ghost-circle"); // Apply CSS class to ghost circle
            ghost.getNode().getStyleClass().add("ghost-label"); // Apply CSS class to ghost label

            ghost.getNode().setLayoutX(x - 20); // Adjusting for circle radius
            ghost.getNode().setLayoutY(y - 20); // Adjusting for circle radius

            ghosts.add(ghost);
            gamePane.getChildren().add(ghost.getNode());
        }
    }


    public KeyFrenzyModel getTheModel() {
        return theModel;
    }

    public VBox getRoot() {
        return root;
    }

    public FlowPane getTopPane() {
        return topPane;
    }

    public Label getLabelMessageBanner() {
        return labelMessageBanner;
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }
}
