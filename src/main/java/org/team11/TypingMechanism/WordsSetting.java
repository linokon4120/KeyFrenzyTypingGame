/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/13/2024
 * Time: 8:11 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.TypingMechanism
 * Class: WordsSetting
 *
 * Description:
 *The WordsSetting class encapsulates settings for a game environment where the squid interacts with ghosts.
 *  It defines the following constants:
 * WORD_DELAY: Specifies the delay, in milliseconds, before a new ghost appears on the screen.(5 seconds)
 * WORD_DURATION: Represents the total time, in milliseconds, taken for a ghost to reach the squid.(7 seconds)
 * GAME_LENGTH: Indicates the total length of the game session, measured in milliseconds (90 seconds).

 * **************************************
 */
package org.team11.TypingMechanism;

public class WordsSetting {
        /** The delay before a new ghost appear on the screen */
        public final static int WORD_DELAY = 5000;
        /** The total time taken for a ghost to reach the main char */
        public final static int WORD_DURATION = 7000;
        /** The total length of the game */
        public final static int GAME_LENGTH = 90000;
}