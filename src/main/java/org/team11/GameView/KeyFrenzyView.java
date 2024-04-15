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

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.team11.GameModel.Ghost;
import org.team11.GameModel.KeyFrenzyModel;
import org.team11.GameModel.MainCharacter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KeyFrenzyView {
    private final KeyFrenzyModel theModel;

    // TODO fix the controller problem :(
    private final org.team11.GameView.KeyFrenzyController theController;
    private VBox root;
    private FlowPane topPane;
    private Label labelMessageBanner;
    private Label currentScore;
    private GridPane gamePane;
    private List<Ghost> ghosts;
    private MainCharacter mainCharacter;
    private TextField userTypeBox;

    private AnimationTimer animationTimer;
    private GhostTimerMovement ghostTimer;


    /**
     /**
     * This is the "view" in the MVC design for the game Key Frenzy. A view class
     * does nothing more than initializes all nodes for the scene graph for this view.
     * @param theModel the model of the game logic.
     */
    public KeyFrenzyView(KeyFrenzyModel theModel) {
        this.theModel = theModel;
        this.theController = new org.team11.GameView.KeyFrenzyController();
        theController.initialize();
        initSceneGraph();
        initializeAnimationTimer();
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
        configuringMessageBanner();

        ghostTimer = new GhostTimerMovement();


        // Initialize ghosts
        this.ghosts = new ArrayList<>();
        initializeGhosts();

        //Initialise the main Character
        initializeMainCharacter();


        //Adding the text box to the game
        gamePane.add(userTypeBox, 10, 10);



        this.root.getChildren().add(labelMessageBanner);
        this.root.getChildren().add(currentScore);
        this.root.getChildren().add(gamePane);
    }

    /**
     * Initialises the main character and adds it to the center of the grid pane
     */
    private void initializeMainCharacter() {
        // Create and position the main character (circle) in the center cell
        mainCharacter = new MainCharacter();

        //Adding the main character to the center of the game
        gamePane.add(mainCharacter.getNode(), 39, 33);
        mainCharacter.getNode().getStyleClass().add("main-character");

    }


    /**
     * Adds the message banner into the home screen of the came
     */
    private void configuringMessageBanner() {
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
    }

    /**
     * Starts the initialises and starts the animation timer
     */
    private void initializeAnimationTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ghostTimer.handle(now);

            }
        };

        animationTimer.start();
    }


    /**
     * Initialises the ghosts and maps them into the game pane
     */
    private void initializeGhosts() {
        Dictionary dictionary = new Dictionary();

        // This is just a random dictionary. I'm waiting for the dictionary class
        String[] words = dictionary.getWords(3, 4).toArray(new String[0]);

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

    /*
    Animation timer and Ghost mobility
     */

    private class GhostTimerMovement extends AnimationTimer{

        /** Distance by which the ghost will disintegrate if it is close to the main character*/
        private static final double COLLISION_DISTANCE = 10;

        @Override
        public void handle(long now) {

            double mainCharacterX = mainCharacter.getNode().getLayoutX();
            double mainCharacterY = mainCharacter.getNode().getLayoutY();


            moveAllGhosts(mainCharacterX, mainCharacterY);

            //Check for collisions with the main character
            checkCollisions(mainCharacterX, mainCharacterY);
        }


        /**
         * Calculates the ghost distance form the main character's distance
         * @param gX, ghost X Position
         * @param gY, ghost Y position
         * @param mcX,  x position of the main character
         * @param mcY  y position of the main character
         * @return the vector distance between the ghost and main character
         */

        private double calculateDistance(double gX, double gY, double mcX, double mcY){
            return Math.sqrt(Math.pow(gX - mcX, 2)+ Math.pow(gY-mcY ,2));
        }


        /**
         * Moves the ghosts to wards the main character
         * @param mainCharacterX , x position of the main character
         * @param mainCharacterY , y position of the main character
         */

        private void moveAllGhosts(double mainCharacterX, double mainCharacterY) {
            for (Ghost ghost : ghosts){
                ghost.move(mainCharacterX, mainCharacterY);
            }
        }

        /**
         * Checks how far the ghosts are from the main character and destroys the ghost if
         * the ghost is within the collision distance
         * @param mainCharacterX,  x position of the main character
         * @param mainCharacterY,  y position of the main character
         */
        private void checkCollisions(double mainCharacterX, double mainCharacterY) {
            Iterator <Ghost> iterate = ghosts.iterator();

            while (iterate.hasNext()){
                Ghost ghost = iterate.next();
                double distance = calculateDistance(ghost.getX(), ghost.getY(), mainCharacterX, mainCharacterY);

                if(distance <= COLLISION_DISTANCE) {
                    //Destroy Ghost
                    iterate.remove();
                    destroy(ghost);

                }

            }
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
                destroy(ghost);

                //TODO Update the score

//                theController.onKeyPressed(user);
                break;
            }
        }
    }


    /**
     * Makes the Ghosts disappear from the game pane,
     * @param ghost ,the ghost to be destroyed
     */
    private void destroy(Ghost ghost) {
        gamePane.getChildren().remove(ghost.getNode());
    }


    /*
    Getter methods
     */
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
