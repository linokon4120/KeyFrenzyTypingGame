/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/17/2024
 * Time: 2:50 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.TypingMechanism
 * Class: GhostAnimation
 *
 * Description:
 * Manages the animation and movement of ghost entities
 * timer: A Timer object used for scheduling tasks related to ghost animation.
 * text: A JavaFX Text object representing the textual content associated with the ghost animation.
 * lastMovementTime: A static long variable representing the time of the last ghost movement.
 * isPaused: A boolean flag indicating whether the animation is currently paused.
 *
 * The class provides a constructor to initialize a GhostAnimation object with a Timer instance and a Ghost object.
 * It initializes the lastMovementTime variable and sets isPaused to false.
 * The start() method initiates the animation loop using an AnimationTimer.
 * Within this loop, the animation logic is executed if the animation is not paused.
 * The pause() method pauses the animation by halting the execution of the Timer object, effectively suspending the animation.
 * It sets isPaused to true.
 * The stop() method stops the animation by canceling the Timer object and removing the associated text element from its parent Pane.
 * **************************************
 */
package org.team11.Ghosts;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Timer;

public class GhostAnimation {

    //Schedules tasks related to ghost animations
    public Timer timer;

    //the text associated with the ghost movement
    public Text text;

    //the time of last ghost movement
    public static long lastMovementTime;

    //Checks if the game is paused
    public boolean isPaused;


    /**
     * A constructor for the ghost Animation class
     * @param timer scheduling tasks related to ghost animation.
     * @param ghost the animated ghost
     */
    public GhostAnimation(Timer timer, Ghost ghost) {
        this.timer = timer;
        lastMovementTime = System.currentTimeMillis();
        isPaused = false;

    }

    /**
     * Starts the  animation
     */
    public void start() {
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (!isPaused) {
                    // Animation logic here

                }
            }
        }.start();
    }


    /**
     * Pauses the animation
     *
     * @throws InterruptedException if an interruption occurs
     */
    public void pause() throws InterruptedException {

        if (timer != null)
            timer.cancel();

        if (text != null)
            ((Pane) text.getParent()).getChildren().remove(text);
        isPaused = true;
    }


    /**
     * Stops the animation
     */
    public void stop() {
        if (timer != null)

            timer.cancel();
        if (text != null)
            ((Pane) text.getParent()).getChildren().remove(text);
    }

}

