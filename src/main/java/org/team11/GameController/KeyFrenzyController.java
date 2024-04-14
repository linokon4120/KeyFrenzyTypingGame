/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/12/2024
 * Time: 2:37 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameController
 * Class: KeyFrenzyController
 *
 * Description:
 *
 * **************************************
 */

package org.team11.GameController;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.*;
import org.team11.TypingMechanism.*;
import org.team11.GameView.Dictionary;

public class KeyFrenzyController {
    @FXML
    public Text infoText;

    @FXML
    public TextField textInput;

    @FXML
    public Pane wordPane;

    private final Map<String, WordsTimer> wordTimers = new HashMap<>();

    private Timer globalAnimationTimer;

//    private TyperGame game;

    private Random rand;

    private boolean lose;
    private Dictionary dict;

    /**
     * Method that runs when the game starts
     */
    @FXML
    protected void initialize() {
        // Create a new game and random instance
//        game = new TyperGame();
        rand = new Random(System.currentTimeMillis());

        // Start a new game with the "lost" state being in false
        lose = false;

        // Create a timer that will create a new word every 5 seconds
        globalAnimationTimer = new Timer();
        globalAnimationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
//                generateNewWord();
            }
        }, 10, WordsSetting.WORD_DELAY);
    }

    @FXML
    protected void onKeyPressed(KeyEvent e) {
        // If lost, ignore keystrokes
        if (lose) return;

        // If the user presses the enter or space key
        if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
            // Submit word
            String text = textInput.getText().trim().toLowerCase(Locale.ROOT);
            GuessStatus guessStatus = dict.guess(text);

            // Clear the box
            textInput.setText("");

            // Do stuff based on the guess
            switch (guessStatus) {
                case CORRECT:
                    infoText.setText("Typed " + text + " | Score: " + dict.getScore());
                    wordTimers.get(text).stop();
                    wordTimers.remove(text);
                    break;
                case INVALID_WORD:
                    infoText.setText(text + " is not a valid word!");
                    break;
                case WRONG:
                    infoText.setText(text + " is not on the screen!");
                    break;
            }
        }
    }

    private void generateNewLabelOnGhost() {
        // Generate the new word
        String word = dict.getwords().get(0);

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

    private Text createAnimation(String word) {
        // Create the text object
        Text text = new Text(word);
        text.setFont(Font.font("Comic Sans MS" ,24));

        // Run the animation on the FX App thread
        Platform.runLater(() -> {
            // Get x and y coords of the word
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

    private void gameOver() {
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