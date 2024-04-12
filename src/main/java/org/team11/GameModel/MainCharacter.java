/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Holiness Kerandi
 * Section: 02
 * Date: 4/11/24
 * Time: 11:20 AM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameModel
 * Class: MainCharacter
 *
 * Description:
 *
 * ****************************************
 */
package org.team11.GameModel;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MainCharacter {
    private Circle node;

    public MainCharacter() {

        // Create Main Character
        node = new Circle(20);
        node.setFill(Color.RED);


    }

    public Circle getNode(){
        return node;
    }

    /**
     * Sets the position of the main Character
     * @param centerX x co-ordinate of the main character
     * @param centerY y co-ordinate of the main character
     */

    public void setPosition(double centerX, double centerY){
        //Calculate the position relative to the centre of game pane
        double x = centerX - node.getRadius();
        double y = centerY - node.getRadius();

        //Set the position of the main character
        node.setLayoutX(x);
        node.setLayoutY(y);
    }
}