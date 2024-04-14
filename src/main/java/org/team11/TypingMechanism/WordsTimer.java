/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/13/2024
 * Time: 8:28 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameController
 * Class: WordsTimer
 *
 * Description:
 *
 * **************************************
 */
package org.team11.TypingMechanism;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Timer;

public class WordsTimer {
    public Timer timer;
    public Text text;

    public WordsTimer(Timer timer, Text text) {
        this.timer = timer;
        this.text = text;
    }

    public void stop() {
        if (timer != null)
            timer.cancel();
        if (text != null)
            ((Pane) text.getParent()).getChildren().remove(text);
    }
}