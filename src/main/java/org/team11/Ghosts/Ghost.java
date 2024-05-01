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
 * The Ghost class encapsulates the characteristics and behavior of ghost entities in the game.
 *It includes the following attributes:
 * word: A string representing the word associated with the ghost that the player needs to type.
 * active: A boolean flag indicating whether the ghost is currently active in the game.
 * label: A JavaFX Label object displaying the word associated with the ghost.
 * node: A JavaFX Pane object containing the graphical representation of the ghost, including its circle shape and label.
 * creationTime: A long value representing the time at which the ghost was created.
 *
 * The class provides a constructor to initialize a Ghost object with a given word and grid size.
 * It also includes a private method, initializeGhost, to set up the visual representation of the ghost using JavaFX element
 * the class also provides getter methods to access the attributes of the ghost, such as the word, activity status, graphical node, and creation time
 * **************************************
 */
package org.team11.Ghosts;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class Ghost {

    //The word associated with a ghost
    private final String word;

    //checks if the ghost is active in the game
    private boolean active;

    //graphical representation of the ghost
    private Pane node;

    //the time the ghost was created
    private long creationTime;

    /**
     * Constructor for the ghost class
     * @param word to be typed
     */
    public Ghost(String word) {
        this.word = word;
        this.active = true;

        try {
            initializeGhost(word);
        } catch (Exception e) {
            // Handle the exception gracefully
            System.err.println("Error initializing Ghost: " + e.getMessage());
            // Optionally, set active to false or perform fallback behavior
            this.active = false;
            this.node = null;
        }
    }



    /**
     * Creates and positions the words on the ghost and the ghost on the game pane
     * Sets up the visual representation of the ghost using the JAVA FX methods
     * @param word, the word to be typed by the player
     *
     *
     */

    private void initializeGhost(String word) {

        // Create a circle for the ghost
        Circle circle = new Circle(39); // Radius of the circle
        circle.setFill(Color.TRANSPARENT); // Set fill color to transparent

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKBLUE);
        dropShadow.setOffsetX(0f);
        dropShadow.setOffsetY(0f);
        dropShadow.setWidth(31);
        dropShadow.setHeight(31);
        circle.setEffect(dropShadow);

        // Set background image using CSS
        Image img = new Image(getClass().getResourceAsStream("/animation/ghost1.gif"));
        circle.setFill(new ImagePattern(img));

        // Create a label for the word
        Label label = new Label(word);
        label.setFont(Font.font("Futura", 15));
        label.setTextFill(Color.web("#fdf0d5"));
        label.setEffect(dropShadow);

        // Position the label at the top of the circle
        label.layoutXProperty().bind(circle.centerXProperty().subtract(label.widthProperty().divide(2)));
        label.layoutYProperty().bind(circle.centerYProperty().subtract(circle.radiusProperty()).subtract(label.heightProperty()));

        // Create a new pane to contain the circle and label and sets the node
        this.node = new Pane(circle, label);
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

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getCreationTime() {
        return creationTime;
    }
}
