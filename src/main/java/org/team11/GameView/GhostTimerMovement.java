/* ***************************************
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
package org.team11.GameView;

/*
    Animation timer and Ghost mobility
     */

import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;
import org.team11.GameController.KeyFrenzyView;

import java.util.Iterator;
import java.util.List;

public class GhostTimerMovement extends AnimationTimer {

    /**
     * Distance by which the ghost will disintegrate if it is close to the main character
     */
    private static final double COLLISION_DISTANCE = 1;
    private GridPane gamePane;
    private List<Ghost> ghosts;
    private KeyFrenzyView theView;


    @Override
    public void handle(long now) {

        // Get the dimensions of the GridPane
        double gridPaneWidth = gamePane.getWidth();
        double gridPaneHeight = gamePane.getHeight();

        // Calculate the center coordinates of the GridPane
        double centerX = gridPaneWidth / 2.0;
        double centerY = gridPaneHeight / 2.0;
        System.out.println("Centre X: " + centerX);
        System.out.println("Centre Y:" + centerY);


        //Check for collisions with the main character

    }

}

    /**
     * Calculates the ghost distance form the main character's distance
     * @param gX, ghost X Position
     * @param gY, ghost Y position
     * @param mcX,  x position of the main character
     * @param mcY  y position of the main character
     * @return the vector distance between the ghost and main character
     */




    /**
     * Moves the ghosts to wards the main character
     * @param mainCharacterX , x position of the main character
     * @param mainCharacterY , y position of the main character
     */

