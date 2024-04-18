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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
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

    // TODO fix the controller problem :(
    private VBox root;
    private FlowPane topPane;
    private Label labelMessageBanner;
    private Label currentScore;
    private GridPane gamePane;
    private List<Ghost> ghosts;
    private Ghost ghost;
    private final Map<String, GhostAnimation> wordTimers = new HashMap<>();
    private TextField userTypeBox;
    private WordDictionary wordDictionary;
    private Random rand;
    private boolean lost;
    private Timer globalTimer;
    private String userName;

    private double paneWidth = 800;
    private double paneHeight = 600;


    private AnimationTimer animationTimer;
    private GhostTimerMovement ghostTimer;


    /**
     /**
     * This is the "view" in the MVC design for the game Key Frenzy. A view class
     * does nothing more than initializes all nodes for the scene graph for this view.
     */
    public KeyFrenzyView(String username) {
        this.userName = username;

        wordDictionary = new WordDictionary();

        lost = false;
        rand = new Random(System.currentTimeMillis());


        initSceneGraph();
//        initializeAnimationTimer();

        globalTimer = new Timer();
        globalTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                generateNewWord();
            }
        }, 5, WordsSetting.WORD_DELAY); // 5 is the time delayed before the first ghost appaers
    }


    /**
     * Initialize the entire scene graph
     */
    public void initSceneGraph() {


        // Initialize the root pane
        this.root = new VBox();
        // Create and configure the game pane
        gamePane = new GridPane();
        // Set minimum size for the gamePane
        gamePane.setMinWidth(800); // Set minimum width
        gamePane.setMinHeight(600); // Set minimum height

        this.gamePane.getStyleClass().add("game-pane"); // Apply CSS class to gamePane

        // Create and configure the message banner
        configuringMessageBanner();

        ghostTimer = new GhostTimerMovement();

        // Initialize ghosts
        this.ghosts = new ArrayList<>();

        //Adding the text box to the game
        gamePane.add(userTypeBox, 10, 10);
        // Display the username in the middle of the view
        Text userNameText = new Text(userName);
        userNameText.setStyle("-fx-font-size: 24;");
        userNameText.setStyle("-fx-background-color: WHITE");
        gamePane.add(userNameText, 50,50);


        this.root.getChildren().add(labelMessageBanner);
        this.root.getChildren().add(currentScore);
        this.root.getChildren().add(gamePane);
    }


    /**
     * Adds the message banner into the home screen of the came
     */
    private void configuringMessageBanner() {
        labelMessageBanner = new Label("Type words on ghosts to destroy them!");
        currentScore = new Label("Current Score: ");
        this.currentScore.getStyleClass().add("current-score");
        this.userTypeBox = new TextField();


        // TODO Modify how we handle the user input to regenerate a new ghost
        userTypeBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
                String textInput = userTypeBox.getText().trim().toLowerCase(Locale.ROOT);

                handleUserInput(userTypeBox.getText().trim());
                userTypeBox.clear();

                GuessStatus guessStatus = wordDictionary.guess(textInput);

            }});
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
//                wordTimers.get(userInput).stop();
//                wordTimers.remove(userInput);
                //TODO Update the score
                break;
            }
            if (!ghost.isActive()) {

                String[] words = wordDictionary.getWords(3, 4).toArray(new String[0]);

                Ghost ghostNew = new Ghost(words[0],80);
                // Apply CSS class to the ghost
                ghost.getNode().getStyleClass().add("ghost-circle");
                ghost.getNode().getStyleClass().add("ghost-label");
                gamePane.getChildren().add(ghost.getNode());
            }
        }
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


    private void generateNewWord() {
        // Generate the new word
        String word = wordDictionary.getWord();

        // Create a timer that ends the game if the player
        // does not type the word in time
        Timer wordTimer = new Timer();
        wordTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // When the timer runs out (8 seconds), that means the player loses
                lost = true;
                gameOver();
            }
        }, WordsSetting.GAME_LENGTH);

        // Add the words to the global map and
        // draw it on the screen
        List<Ghost> ghostsOnScreen = createAnimation(word);
        ghosts.add(ghostsOnScreen.get(0));
        ghosts.add(ghostsOnScreen.get(1));
        wordTimers.put(word, new GhostAnimation(wordTimer, ghostsOnScreen.get(0)));
        wordTimers.put(word, new GhostAnimation(wordTimer, ghostsOnScreen.get(1)));
    }

    private List<Ghost> createAnimation(String word) {
        // Tie to the text on top of the ghosts
        // Run the animation on the FX App thread
        // The movement of the ghosts should be towards the middle of the screen
        // Generate a path
        // Generate animation
        // Add to the gamePane
        // Return text on top of the ghost that was destroyed

        // Create the text object
        List<Ghost> ghostsOnScreen = new ArrayList<>();
        String[] words = wordDictionary.getWords(3, 4).toArray(new String[0]);

        Ghost ghost1 = new Ghost(words[0],80);
        Ghost ghost2 = new Ghost(words[1],80);
        ghostsOnScreen.add(ghost1);
        ghostsOnScreen.add(ghost2);


        // Run the animation on the FX App thread
        Platform.runLater(() -> {
            // Get dimensions of the wordPane


            // Get x and y coords of the word
            double x1 = paneWidth/2;
            double y1 = rand.nextDouble() * paneHeight;

            // Get x and y coords of the word
            double x2 = paneWidth;
            double y2 = rand.nextDouble() * paneHeight;


            // Generate a path
            Path path1 = new Path();
            path1.getElements().add(new MoveTo(-50, y1));
            path1.getElements().add(new LineTo(paneWidth/2, paneHeight/2));

            // Generate a path
            Path path2 = new Path();
            path2.getElements().add(new MoveTo(x2 + 50, y2));
            path2.getElements().add(new LineTo(paneWidth/2, paneHeight/2));


            // Generate animation
            createPath(path1, ghost1);
            createPath(path2, ghost2);


            // Add to pane
            gamePane.getChildren().add(ghost1.getNode());
            gamePane.getChildren().add(ghost2.getNode());

        });

        return ghostsOnScreen;

    }

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
                // Transfer game object to game over controller
                GameOverController newController = SceneSwitch.change(currentScore, "game-over-view.fxml");

                // Move to the game over screen
                newController.transferData(wordDictionary);
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
