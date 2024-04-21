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
package org.team11.GameController;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.team11.GameView.Ghost;
import org.team11.GameView.GhostTimerMovement;
import org.team11.GameView.WordDictionary;
import org.team11.TypingMechanism.GhostAnimation;
import org.team11.TypingMechanism.GuessStatus;
import org.team11.TypingMechanism.WordsSetting;

import java.io.IOException;
import java.util.*;

public class KeyFrenzyView {

    public static final double COLLISION_DISTANCE = 1;
    private VBox root;
    private HBox bottomPane;
    private Label labelMessageBanner;
    private Label currentScore;
    private Label usernameLabel;
    private Label timeUsedLabel;
    private GridPane gamePane;
    private List<Ghost> ghosts;
    private Ghost ghost;
    private final Map<String, GhostAnimation> wordTimers = new HashMap<>();
    private TextField userTypeBox;
    private final WordDictionary wordDictionary;
    private final Random rand;
    private boolean lost;
    private final Timer globalTimer;
    private String userName;
    private ProgressBar healthBar;
    private int lives;



    //The width of the game pane
    private double paneWidth;

    //The height of the game pane
    private double paneHeight;
    /**Variable to check the score*/

    //Variable to check the score
    private int score;

    private GhostTimerMovement ghostTimer;
    private Ghost ghost1;
    private Ghost ghost2;


    /**
     /**
     * This is the "view" in the MVC design for the game Key Frenzy. A view class
     * does nothing more than initializes all nodes for the scene graph for this view.
     */
    public KeyFrenzyView(String username) {
        this.userName = username;
        this.score = 0;

        this.wordDictionary = new WordDictionary();
        this.lost = false;
        this.rand = new Random(System.currentTimeMillis());

        initSceneGraph();
//        initializeAnimationTimer();

        this.globalTimer = new Timer();
        this.globalTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                generateNewWord();
            }
        }, 5, WordsSetting.WORD_DELAY); // 5 is the time delayed before the first ghost appears
    }

    /**
     * Initialize the entire scene graph
     */
    public void initSceneGraph() {

        // Initialize the root pane
        this.root = new VBox();

        // Create and configure the game pane
        gamePane = new GridPane();

        bottomPane = new HBox();

        // Set minimum size for the gamePane
        gamePane.setMinSize(800,600); // Set minimum width
        // TODO Get the paneWidth and paneHeight of the game Pane


        this.gamePane.getStyleClass().add("game-pane"); // Apply CSS class to gamePane

        paneWidth = 800;
        paneHeight = 600;
        // Create and configure the message banner
        configuringMessageBanner();

        ghostTimer = new GhostTimerMovement();

        // Initialize ghosts
        this.ghosts = new ArrayList<>();


        // Display the username in the middle of the view
        Text userNameText = new Text(userName);
        userNameText.setStyle("-fx-font-size: 24;");
        userNameText.setStyle("-fx-background-color: WHITE");
        gamePane.add(userNameText, 50,50);

        // Create a health bar (progress bar) to display remaining health
        this.healthBar = new ProgressBar(1.0); // Full health initially
        this.healthBar.setPrefWidth(200); // Set preferred width
        this.healthBar.setStyle("-fx-accent: green;"); // Customize appearance
        Label healthLabel = new Label("Health: ");
        healthLabel.setStyle("-fx-background-color: WHITE");

        // Initialize lives counter
        this.lives = 3;

        // Layout for health bar and lives counter
        HBox healthBox = new HBox(10, healthLabel, healthBar);
        VBox.setMargin(healthBox, new Insets(10));

        //Adding the text box to the game
        bottomPane.getChildren().addAll(userTypeBox, healthBox);
        this.root.getChildren().addAll(labelMessageBanner, currentScore, gamePane, bottomPane);
    }


    /**
     * Adds the message banner into the home screen of the came
     */
    private void configuringMessageBanner() {
        // Instruction banner
        labelMessageBanner = new Label("Type words on ghosts to destroy them!");
        this.labelMessageBanner.getStyleClass().add("instruct-banner");

        // Current score label
        currentScore = new Label("Current Score: ");
        this.currentScore.getStyleClass().add("current-score");

        // TODO: Figure out why is this not responding to the CSS styling
        // Username label
        usernameLabel = new Label("Username: " + userName);
        this.usernameLabel.getStyleClass().add("user-nickname");

        // Time used label
        timeUsedLabel = new Label("Time Used: 00:00");
        this.timeUsedLabel.getStyleClass().add("time-spent");

        // VBox to contain all components vertically
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER); // Align contents to the center
        container.getChildren().addAll(labelMessageBanner, currentScore, usernameLabel, timeUsedLabel);
        container.getStyleClass().add("message-banner-container");

        // Add container to the game pane
        // Adjust layout constraints as needed
        gamePane.getChildren().add(container);
        // TODO: have this in a bottom pane, probably out of the configuring Message banner
        // TextField for user input
        userTypeBox = new TextField();
        userTypeBox.getStyleClass().add("user-type-box");
        userTypeBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
                String textInput = userTypeBox.getText().trim().toLowerCase(Locale.ROOT);
                handleUserInput(textInput);
                userTypeBox.clear();
                GuessStatus guessStatus = wordDictionary.guess(textInput);
            }
        });
    }



    /**
     * Handle the user input when prompted
     * @param userInput the String input from user
     */
    private void handleUserInput(String userInput) {

        boolean matchFound = false;

        Iterator<Ghost> iterator = ghosts.iterator();
        while (iterator.hasNext()) {
            Ghost ghost = iterator.next();
            if (ghost.getWord().equalsIgnoreCase(userInput)) {

                // Word matched, remove the ghost from the game pane
                destroy(ghost);
                iterator.remove();
                matchFound = true;

                // Update the score
                score += 10;
                updateScoreLabel();
                break;
            }
        }

    }


    /** Updates the score on the game pane */
    private void updateScoreLabel() {
        currentScore.setText("Current Score: " + score);
    }


    /**
     * Starts the initialises and starts the animation timer
     */
    private void initializeAnimationTimer() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ghostTimer.handle(now);
            }
        };

        animationTimer.start();
    }


    /**
     * Starts the timer and generates a new word to be typed by the player
     */
    private void generateNewWord() {
        // Generate the new word
        String word = wordDictionary.getWord();

        // Create a timer that ends the game if the player does not type the word in time.
        Timer wordTimer = new Timer();

        wordTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // When the timer runs out (8 seconds), that means the player loses
                lost = true;
                gameOver();
            }
        }, WordsSetting.GAME_LENGTH);

        // Add the words to the global map and draws it on the screen
        List<Ghost> ghostsOnScreen = createAnimation(word);
        ghosts.add(ghostsOnScreen.get(0));
        ghosts.add(ghostsOnScreen.get(1));

        //Start Ghost Animations
        startGhostAnimation(ghostsOnScreen.get(0));
        startGhostAnimation(ghostsOnScreen.get(1));

        //Store ghost animation in the map
        storeGhostAnimation(word, wordTimer, ghostsOnScreen, 0);
        storeGhostAnimation(word, wordTimer, ghostsOnScreen, 1);
    }

    /**Stores the ghost animation into the map */
    private void storeGhostAnimation(String word, Timer wordTimer, List<Ghost> ghostsOnScreen, int index) {
        wordTimers.put(word, new GhostAnimation(wordTimer, ghostsOnScreen.get(index)));
    }

    /**
     * Starts the ghost animations
     * @param ghost to be animated
     */
    private void startGhostAnimation(Ghost ghost) {
        GhostAnimation animation = wordTimers.get(ghost.getWord());

        if (animation != null){
            animation.start();
        }
    }

    /**
     * Ties the text on top of the ghost,
     * Runs the Animation on the FX app thread,
     * Moves the ghosts towards the middle of the screen
     * Generates a path and an animation, and adds it to the game pane
     * @param word the string of words to be typed
     * @return text on top of the ghost that was destroyed
     */
    private List<Ghost> createAnimation(String word) {

        // Create the text object
        List<Ghost> ghostsOnScreen = new ArrayList<>();
        String[] words = wordDictionary.getWords(3, 4).toArray(new String[0]);

        Ghost ghost1 = new Ghost(words[0],80);
        Ghost ghost2 = new Ghost(words[1],80);
        ghostsOnScreen.add(ghost1);
        ghostsOnScreen.add(ghost2);


        //Animation timer for each ghost
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ghostTimer.handle(now);
            }
        };

        // Run the animation on the FX App thread
        Platform.runLater(() -> {

            // Moves the animation towards the center of the game pane

            // Get x and y coords of the word
            double x1 = paneWidth/2;
            double y1 = rand.nextDouble() * paneHeight;

            // Get x and y coords of the word
            double x2 = paneWidth;
            double y2 = rand.nextDouble() * paneHeight;

            // Generate a path for ghosts coming from left side
            Path path1 = new Path();
            path1.getElements().add(new MoveTo(-50, y1));

            // Moves the path to the middle of the pane
            moveToCenter(ghost1, path1);

            // Generate a path for ghosts coming from right side
            Path path2 = new Path();
            path2.getElements().add(new MoveTo(x2 + 50, y2));
            // Moves the path to the middle of the pane
            moveToCenter(ghost2, path2);

            // Generate animation
            createPath(path1, ghost1);
            createPath(path2, ghost2);

            // Add to pane
            gamePane.getChildren().add(ghost1.getNode());
            gamePane.getChildren().add(ghost2.getNode());
        });

        return ghostsOnScreen;

    }

    /**
     * Moves the ghosts to the center of the game pane
     * @param path to be moved
     */
    private void moveToCenter(Ghost ghost, Path path) {

        double centerX = paneWidth/2;
        double centerY = paneHeight/2;

        path.getElements().add(new LineTo(centerX, centerY));
//        // Calculate distance to the center
//        double distanceToCenter = calculateDistance(ghost.getNode().getLayoutX(), ghost.getNode().getLayoutY(), centerX, centerY);
//
//        // If ghost reaches the center, decrease health and update health bar
//        if (ghost.getNode().getScaleX() <= 10 || ghost.getNode().getScaleY() <= 10) {

        // Create a PathTransition to animate the ghost along the path
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(WordsSetting.WORD_DURATION)); // Set animation duration
        pathTransition.setPath(path); // Set the path for the animation
        pathTransition.setNode(ghost.getNode()); // Set the node (ghost) to animate
        pathTransition.setCycleCount(1); // Animation plays once

        // Event handler to handle animation completion (ghost reaches center)
        if (ghost.isActive()) {
            pathTransition.setOnFinished(event -> {
                // Check if the ghost is still in the game pane
                if (gamePane.getChildren().contains(ghost.getNode())) {
                    // Ghost is still present in the game pane
                    lives--; // Decrease health
                    updateHealthBar(); // Update health bar

                    // Remove the ghost from the game pane
                    destroy(ghost);
                }
            });

            // Start the animation
            pathTransition.play();
        }
    }


    private void updateHealthBar() {
        double healthPercentage = (double) lives / 3.0; // Assuming 3 lives in total
        healthBar.setProgress(healthPercentage);

        if (lives <= 0) {
            // Game over logic (no more health)
            gameOver();
        }
    }


    /**
     * Calculates the ghost distance form the main character's distance
     * @param x, ghost X Position
     * @param y, ghost Y position
     * @param centerX,  x position of the main character
     * @param centerY  y position of the main character
     * @return the vector distance between the ghost and main character
     */

    private double calculateDistance(double x, double y, double centerX, double centerY) {
            return Math.sqrt(Math.pow(x - centerX, 2)+ Math.pow(y-centerY, 2));
    }


    /**
     * Creates a path to be followed by the ghost
     * @param path
     * @param ghost
     */
    private static void createPath(Path path, Ghost ghost) {
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(WordsSetting.WORD_DURATION));
        pt.setPath(path);
        pt.setNode(ghost.getNode());
        pt.setCycleCount(1);
        pt.play();
    }

    private void gameOver() {
        // Perform actions on the main thread
        Platform.runLater(() -> {
            // Stop all timers
            globalTimer.cancel();
            for (GhostAnimation wa : wordTimers.values()) {
                wa.stop();
            }

        // TODO Switch to Game Over view

            try {

                // Load the FXML file. Obtain the root of the scene graph
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/gameOverView.fxml")); // TODO this is only the start menu
                Parent root = loader.load();

                // Transfer game object to game over controller
                Stage primaryStage = new Stage();
                // Set up the stage and show it
                primaryStage.setTitle("Hello FXML!");
                primaryStage.setScene(new Scene(root));
                primaryStage.sizeToScene();
                primaryStage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Makes the Ghosts disappear from the game pane,
     * @param ghost ,the ghost to be destroyed
     */
    public void destroy(Ghost ghost) {
        gamePane.getChildren().remove(ghost.getNode());
    }


    public VBox getRoot() {
        return root;
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
