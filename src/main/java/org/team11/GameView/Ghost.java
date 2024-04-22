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

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.Random;

public class Ghost {
    private static final double MOVEMENT_AMOUNT = 1;
    private String word;
    private boolean active;
    private Label label;
    private Pane node;

    private long creationTime;
    private  final double TO_SECONDS = 1e9;

    private AnimationTimer animationTimer;

    private boolean animationRunning;

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
        Circle circle = new Circle(39); // Radius of the circle
        //circle.setStyle("-fx-graphic: url('./animation/ghost.gif')"); //I DONT KNOW WHY THIS IS NOT WORKING!!!
        circle.setFill(Color.TRANSPARENT);
        //circle.setFill(Color.r gb(163, 255, 214)); // Set fill color to transparent

        // Set background image using CSS
        Image img = new Image("./animation/ghost1.gif");
        circle.setFill(new ImagePattern(img));

        circle.setStyle("-fx-graphic: url('./animation/ghost.gif')");
        //circle.getStyleClass().add("ghost-circle"); // Apply CSS class

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



    /**
     * Makes the ghost disappear
     * */






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

    public void setPosition(double centerX, double centerY) {

    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getCreationTime() {
        return creationTime;
    }
}