package org.team11.GameView;/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/15/2024
 * Time: 2:55 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameView
 * Class: GhostTimerMovement
 *
 * Description:
 *
 * **************************************
 */

import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;
import org.team11.GameController.KeyFrenzyView;

import java.util.Iterator;
import java.util.List;

public class GhostTimerMovement extends AnimationTimer {

    private KeyFrenzyView theView;
    private final GridPane gamePane;
    private final List<Ghost> ghosts;

    // Constructor to initialize necessary components
    public GhostTimerMovement(GridPane gamePane, List<Ghost> ghosts) {
        this.gamePane = gamePane;
        this.ghosts = ghosts;
    }

    @Override
    public void handle(long now) {
        // Calculate the center coordinates of the game pane
        double centerX = gamePane.getWidth() / 2;
        double centerY = gamePane.getHeight() / 2;

        double thresholdDistance = 5;

        // Iterate through each ghost
        Iterator<Ghost> iterator = ghosts.iterator();
        while (iterator.hasNext()) {
            Ghost ghost = iterator.next();

            double distance = calculateDistance(ghost, centerX, centerY);

            // Check if the distance is within the threshold
            if (distance <= thresholdDistance) {
                theView.destroy(ghost);
            }

            // Check if the ghost has reached the center
            if (distance <= thresholdDistance) {
                ghost.stopAnimation();
            }
        }

    }

    // Method to calculate the distance between a ghost and the center
    private double calculateDistance(Ghost ghost, double centerX, double centerY) {
        double ghostX = ghost.getNode().getLayoutX() + ghost.getNode().getBoundsInParent().getWidth() / 2;
        double ghostY = ghost.getNode().getLayoutY() + ghost.getNode().getBoundsInParent().getHeight() / 2;
        return Math.sqrt(Math.pow(ghostX - centerX, 2) + Math.pow(ghostY - centerY, 2));
    }
}




