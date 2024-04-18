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

    /** The x position of the ghosts */
    private  int x;

    /** The y position of the ghosts */
    private int y;

    /** The initial speed of the ghost */
    private final int initialSpeed = 3;

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
    public void move(double mainCharacterX, double mainCharacterY ) {


        double dx = Double.compare(mainCharacterX, x);
        double dy = Double.compare(mainCharacterY, y);

        //update the position of the ghost
        if(Math.abs(dx) <= 1 && Math.abs(dy) <= 1 ){
            x += dx;
            y += dy;

        }else{
            //disintegrates when the ghost is one move to the main character
            destroyGhost();
        }



    }

    /**
     * Makes the ghost disappear when the main character is hit
     * or when the user types the words correctly
     * */

    public void destroyGhost() {
        active = false;
        label.setVisible(false); // Hide the ghost
    }


    // TODO for sprint 3
    public void increaseSpeed(){}




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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}