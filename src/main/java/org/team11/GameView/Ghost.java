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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class Ghost {
    private String word;
    private boolean active;
    private Label label;
    private Pane node;


    /**2000 milliseconds, */
    public static final long STATIONARY_THRESHOLD = 2000;

    private AnimationTimer animationTimer;
    private Runnable onAnimationStopListener;

    boolean moving;
    private long stationaryTime;

    private boolean animationRunning;

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

    public Ghost(){
        boolean animationRunning = false;
    }

    public boolean isAnimationRunning(){
        return animationRunning;
    }

    public void setAnimationTimer(AnimationTimer animationTimer){
        this.animationTimer = animationTimer;
        this.animationTimer.stop();
    }

    public void startAnimation(){
        animationRunning =true;
        animationTimer.start();
    }

    public void setOnAnimationStopListener(Runnable listener){
        this.onAnimationStopListener = listener;
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


        // Create a new pane to contain the circle and label
        Pane pane = new Pane(circle, label);

        // Set the node
        this.node = pane;
    }



    /*
     All getter and setter methods:
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

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


}