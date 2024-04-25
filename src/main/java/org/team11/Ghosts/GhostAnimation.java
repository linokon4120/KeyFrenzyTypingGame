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
 *
 * **************************************
 */
package org.team11.Ghosts;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Timer;

public class GhostAnimation {
    public Timer timer;

    public Text text;
    public static long lastMovementTime;
    private boolean isPaused;



    public GhostAnimation(Timer timer, Ghost ghost) {
        this.timer = timer;
        lastMovementTime = System.currentTimeMillis();
        isPaused = false;

    }

    public void start() {
//        lastMovementTime = System.currentTimeMillis();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (!isPaused) {
                    // Animation logic here
                }
            }
        }.start();
    }

    public void pause() throws InterruptedException {
        if (timer != null)
            timer.wait();
//        else {
//
//        }
//        if (text != null)
//            ((Pane) text.getParent()).getChildren().remove(text);
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

