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
package org.team11.GameView;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ghost {
    private static final double MOVEMENT_AMOUNT = 1;
    private String word;
    private boolean active;
    private Label label;
    private Pane node;

    /** The x position of the ghosts */
    private  double x;

    /** The y position of the ghosts */
    private double y;

    /**
     * A Constructor for the ghost class
     */

    public Ghost(String word, int gridSize) {
        this.word = word;
        this.active = true;

        initializeGhost(word, gridSize);
    }

    /**
     * Creates and positions the words on the ghost and the ghost on the game pane
     * @param word, the word to be typed by the player
     * @param gridSize, the size of the game pane
     *
     */

    private void initializeGhost(String word, int gridSize) {
        // Create a circle for the ghost
        Circle circle = new Circle(20); // Radius of the circle
        circle.setFill(Color.rgb(163, 255, 214)); // Set fill color to transparent

        // Set background image using CSS
        //circle.setStyle("-fx-background-image: url('" + getClass().getResource("resources/animation/ghost.gif").toExternalForm() + "'); " + "-fx-background-size: cover;");

        // Create a label for the word
        Label label = new Label(word);
        label.setFont(Font.font("Futura", 12));
        label.setTextFill(Color.rgb(163, 255, 214));

        // Position the label at the top of the circle
        label.layoutXProperty().bind(circle.centerXProperty().subtract(label.widthProperty().divide(2)));
        label.layoutYProperty().bind(circle.centerYProperty().subtract(circle.radiusProperty()).subtract(label.heightProperty()));

        // Initiating the starting position of the ghosts
        // TODO: To be fixed (ensure within grid size)
        Random random = new Random();
        int x = random.nextInt(gridSize);
        int y = random.nextInt(gridSize);

        // Create a new pane to contain the circle and label
        Pane pane = new Pane(circle, label);

        // Set the node
        this.node = pane;
    }



    /* Ghost Mobility */

    /**
     * The ghosts move towards the position of the main character,
     * collides when it is one box to the main character and the gif does its thing.
     *
     * @param mainCharacterX, the x position of the main character
     * @param mainCharacterY, the y position of the main character
     */
    public void move(double mainCharacterX, double mainCharacterY) {
        // Calculate the direction vector towards the main character
        double dx = mainCharacterX - x;
        double dy = mainCharacterY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Update the position of the ghost if it's not too close to the main character
        if (distance > MOVEMENT_AMOUNT) {
            // Normalize the direction vector
            dx /= distance;
            dy /= distance;

            // Calculate the movement amount for this update
            double moveX = dx * MOVEMENT_AMOUNT;
            double moveY = dy * MOVEMENT_AMOUNT;

            // Update the position of the ghost
            x += moveX;
            y += moveY;
        } else {
            // Ghost disintegrates when it's close to the main character
            destroyGhost();
        }
    }


    /**
     * Makes the ghost disappear
     * */

    public void destroyGhost() {
        active = false;
        label.setVisible(false); // Hide the ghost
    }




    /*
     All getter methods:
    */

    public String getWord() {
        return word;
    }

    public boolean isActive() {
        return active;
    }

    public Node getNode() {
        return node;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}