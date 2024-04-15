/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Holiness Kerandi
 * Section: 02
 * Date: 4/15/24
 * Time: 12:51 AM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameView
 * Class: KeyFrenzyController
 *
 * Description:
 *
 * ****************************************
 */
package org.team11.GameView;


import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.team11.TypingMechanism.WordsSetting;
import org.team11.TypingMechanism.WordsTimer;

import java.util.*;

public class KeyFrenzyController {
    @FXML
    public TextField textInput;
    @FXML
    public Pane wordPane;
    private final Map<String, WordsTimer> wordTimers = new HashMap<>();
    private Timer globalAnimationTimer;
    private Random rand;
    private boolean lose;
    private Dictionary dict = new Dictionary();


    /**
     * Method that runs when the game starts
     */
    @FXML
    public void initialize() {

        // Create a new game and random instance
        rand = new Random(System.currentTimeMillis());

        // Start a new game with the "lost" state being in false
        lose = false;
        addStartGameButton();

        // Create a timer that will create a new word every 5 seconds
        globalAnimationTimer = new Timer();

        globalAnimationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                generateNewLabelOnGhost();
            }
        }, 10, WordsSetting.WORD_DELAY);
    }

    /**
     * A button that prompts the user to start the game
     */
    private void addStartGameButton(){
        Button start = new Button("Press Start Game ");
        start.setOnAction( event -> startGame());
    }

    public void startGame() {
        System.out.println("Game Started");
    }

//    /**
//     * This method should evaluate the user input once they hit the
//     * enter key or space key
//     * @param e
//     */
//    @FXML
//    public void onKeyPressed(KeyEvent e) {
//        // If lost, ignore keystrokes
//        if (lose || dict == null) return;
//
//        // If the user presses the enter or space key
//        if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
//            // Submit word
//            String text = textInput.getText().trim().toLowerCase(Locale.ROOT);
//            GuessStatus guessStatus = dict.guess(text);
//
//            // Clear the box
//            textInput.setText("");
//
//            // Do stuff based on the guess
//            switch (guessStatus) {
//                case CORRECT:
//                    wordTimers.get(text).stop();
//                    wordTimers.remove(text);
//                    break;
//                case INVALID_WORD, WRONG:
//                    break;
//            }
//        }
//    }

    public void generateNewLabelOnGhost() {
        // Generate the new word
        String word = dict.getWords().get(0);

        // Create a timer that ends the game if the player
        // does not type the word in time
        Timer wordTimer = new Timer();
        wordTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // When the timer runs out (8 seconds), that means the player loses
                lose = true;
                gameOver();
            }
        }, WordsSetting.WORD_DURATION);

        // Add the words to the global map and
        // draw it on the screen
        Text text = createAnimation(word);
        wordTimers.put(word, new WordsTimer(wordTimer, text));
    }

    public Text createAnimation(String word) {
        // Create the text object
        Text text = new Text(word);
        text.setFont(Font.font("Comic Sans MS" ,24));

        // Run the animation on the FX App thread
        Platform.runLater(() -> {
            // Get x and y coords of the word
            wordPane = new Pane();
            double x = wordPane.getWidth();
            double y = rand.nextDouble() * wordPane.getHeight();

            // Generate a path
            Path path = new Path();
            path.getElements().add(new MoveTo(-50, y));
            path.getElements().add(new LineTo(x + 50, y));

            // Generate animation
            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.millis(WordsSetting.WORD_DURATION));
            pt.setPath(path);
            pt.setNode(text);
            pt.setCycleCount(1);
            pt.play();

            // Add to pane
            wordPane.getChildren().add(text);
        });

        return text;
    }

    public void gameOver() {
        // Perform actions on the main thread
        Platform.runLater(() -> {
            // Stop all timers
            globalAnimationTimer.cancel();
            for (WordsTimer wa : wordTimers.values()) {
                wa.stop();
            }

//            try {
//                // Transfer game object to game over controller
//                GameOverController newController = StageChanger.change(infoText, "game-over-view.fxml");
//
//                // Move to the game over screen
//                newController.transferData(game);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        });
    }
}