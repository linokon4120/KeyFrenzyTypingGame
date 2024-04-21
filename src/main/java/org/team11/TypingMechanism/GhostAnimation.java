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
package org.team11.TypingMechanism;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.team11.GameView.Ghost;

import java.util.Timer;

public class GhostAnimation {

    private Ghost ghost;
    public Timer timer;
    public Text text;
    public static long lastMovementTime;

    public double centerThreshold;

    public GhostAnimation(Timer timer, Ghost ghost) {
        this.timer = timer;
        lastMovementTime = System.currentTimeMillis();
    }

    public void start() {
        lastMovementTime = System.currentTimeMillis();

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

