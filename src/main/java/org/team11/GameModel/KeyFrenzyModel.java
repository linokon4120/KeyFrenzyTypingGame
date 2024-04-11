/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/8/2024
 * Time: 2:31 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameModel
 * Class: KeyFrenzyModel
 *
 * Description:
 *
 * **************************************
 */
package org.team11.GameModel;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

public class KeyFrenzyModel {
    private String currentWord;
    private int score;
    private final ArrayList<Ghost> ghosts;
    private Pane node;

    public KeyFrenzyModel() {
        this.currentWord = generateRandomWord();
        this.score = 0;
        this.ghosts = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            this.ghosts.add(new Ghost("fake ghost", 400));
        }
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }
    public Node getNode() {
        return node;
    }

    public void updateCurrentWord(String typedCharacter) {
        // Update the current word based on typed characters
        currentWord += typedCharacter;
    }

    public void generateNewWord() {
        // Generate a new random word for the next ghost
        currentWord = generateRandomWord();
    }

    public void incrementScore() {
        // Increment player's score when a ghost is destroyed
        score++;
    }

    private String generateRandomWord() {
        // Generate a random word for the ghost
        String[] words = {"ghost", "spooky", "boo", "haunt", "scary"};
        return words[new Random().nextInt(words.length)];
    }
}