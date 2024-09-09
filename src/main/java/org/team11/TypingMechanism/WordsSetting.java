/* ***************************************
 *
 * Scrum Master: Ellyn Ngo
 * Product Owner: Hannah Tran
 * Developer: Holiness Kerandi, Rahul Sibal
 * Project: Key Frenzy
 * Package: org.team11.TypingMechanism
 * Class: WordsSetting
 *
 * Description:
 * The WordsSetting class encapsulates settings for a game environment where the squid interacts with ghosts.
 * It defines the following constants:
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
        public final static int WORD_DURATION = 8000;
        /** The total length of the game */
        public final static int GAME_LENGTH = 90000;
}