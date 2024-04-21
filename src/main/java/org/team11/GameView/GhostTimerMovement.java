package org.team11.GameView;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;
import org.team11.GameController.KeyFrenzyView;

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
