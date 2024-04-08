/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/8/2024
 * Time: 3:07 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameModel
 * Class: Ghost
 *
 * Description:
 *
 * **************************************
 */
package org.team11.GameModel;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ghost {
    private String word;
    private boolean active;
    private Label label;
    private Pane node;


    public Ghost(String word) {
        this.word = word;
        this.active = true;

        // Create a circle for the ghost
        Circle circle = new Circle(20); // Radius of the circle
        circle.setFill(Color.YELLOW); // Set fill color

        // Create a label for the word
        Label label = new Label(word);
        label.setFont(Font.font("Arial", 12));
        label.setTextFill(Color.BLACK);

        // Position the label at the center of the circle
        label.layoutXProperty().bind(circle.centerXProperty().subtract(label.widthProperty().divide(2)));
        label.layoutYProperty().bind(circle.centerYProperty().subtract(label.heightProperty().divide(2)));


        // Create a new pane to contain the circle and label
        this.node = new Pane(circle, label);
    }

    public void destroy() {
        active = false;
        label.setVisible(false); // Hide the ghost
    }


    public void move() {
        // Example: Implement movement logic towards the character
        // This could involve changing the label's position over time
    }

    private String generateRandomWord() {
        // Example: Generate a random word for the ghost
        String[] words = {"ghost", "spooky", "boo", "haunt", "scary"};
        return words[new Random().nextInt(words.length)];
    }


    // All getter methods:

    public String getWord() {
        return word;
    }

    public boolean isActive() {
        return active;
    }

    public Node getNode() {
        return node;
    }

}