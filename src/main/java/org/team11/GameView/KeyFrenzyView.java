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
 * Description: This is the main view class
 * of the game, including every detail that will
 * show up on the screen once hit Run in the GameMain
 *
 * **************************************
 */
package org.team11.GameView;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.team11.GameController.KeyFrenzyController;
import org.team11.GameModel.Ghost;
import org.team11.GameModel.KeyFrenzyModel;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KeyFrenzyView {
    private final KeyFrenzyModel theModel;
    private KeyFrenzyController theController;
    private VBox root;
    private FlowPane topPane;
    private Label labelMessageBanner;
    private Label currentScore;
    private GridPane gamePane;
    private List<Ghost> ghosts;
    private Circle mainCharacter;
    private TextField userTypeBox;


    /**
     /**
     * This is the "view" in the MVC design for the game Key Frenzy. A view class
     * does nothing more than initializes all nodes for the scene graph for this view.
     * @param theModel the model of the game logic.
     */
    public KeyFrenzyView(KeyFrenzyModel theModel) {
        this.theModel = theModel;
        this.theController = new KeyFrenzyController();
        theController.initialize();
        initSceneGraph();
    }

    /**
     * Initialize the entire scene graph
     */
    public void initSceneGraph() {
        // Initialize the root pane
        this.root = new VBox();


        // Create and configure the game pane
        gamePane = new GridPane();
        this.gamePane.getStyleClass().add("game-pane"); // Apply CSS class to gamePane

        // Create and configure the message banner
        labelMessageBanner = new Label("Type words on ghosts to destroy them!");
        currentScore = new Label("Current Score: ");
        this.currentScore.getStyleClass().add("current-score");
        this.userTypeBox = new TextField();
        userTypeBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
                handleUserInput(userTypeBox.getText().trim());
                userTypeBox.clear();
            }
        });

        // Initialize ghosts
        this.ghosts = new ArrayList<>();
        initializeGhosts();

        // Create and position the main character (circle) in the center cell
        mainCharacter = new Circle(20); // Radius of the circle
        mainCharacter.setFill(Color.RED); // Set fill color
        mainCharacter.getStyleClass().add("main-character");

        // Add the main character to the center cell
        gamePane.add(mainCharacter, 40, 40);
        gamePane.add(userTypeBox, 10, 10);

        this.root.getChildren().add(labelMessageBanner);
        this.root.getChildren().add(currentScore);
        this.root.getChildren().add(gamePane);
    }



    private void initializeGhosts() {
        Dictionary dictionary = new Dictionary();

        // This is just a random dictionary. I'm waiting for the dictionary class
        String[] words = dictionary.getwords(3, 4).toArray(new String[0]);

        // Create and position four ghosts with words in the grid
        for (int i = 0; i < 4; i++) {
            Ghost ghost = new Ghost(words[i],80);
            // Apply CSS class to the ghost
            ghost.getNode().getStyleClass().add("ghost-circle");
            ghost.getNode().getStyleClass().add("ghost-label");

            // Add each ghost to a specific cell in the grid
            // TODO: Need to fix the location of the ghosts (currently not in middle)
            switch (i) {
                case 0: // Top center
                    gamePane.add(ghost.getNode(), 40, 0);
                    break;
                case 1: // Bottom center
                    gamePane.add(ghost.getNode(), 40, 70);
                    break;
                case 2: // Left center
                    gamePane.add(ghost.getNode(), 0, 40);
                    break;
                case 3: // Right center
                    gamePane.add(ghost.getNode(), 100, 40);
                    break;
            }

            ghosts.add(ghost);
        }
    }

    /**
     * Handle the user input when prompted
     * @param userInput the String input from user
     */
    private void handleUserInput(String userInput) {
        for (Ghost ghost : ghosts) {
            if (ghost.getWord().equalsIgnoreCase(userInput)) {
                // Word matched, remove the ghost from the game pane
                gamePane.getChildren().remove(ghost.getNode());

//                theController.onKeyPressed(user);
                break;
            }
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
