/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/20/2024
 * Time: 9:03 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.Ghost;
 * Class: GhostTest
 *
 * Description: A test for the ghost class
 * **************************************
 */
package org.team11.Ghosts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GhostTest {

    private Ghost ghost;

    @BeforeEach
    void setUp() {
        ghost = new Ghost("team11");

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getWord() {
        assertEquals("team11", ghost.getWord());
    }


    @Test
    void setCreationTime() {
        long time = System.currentTimeMillis();
        ghost.setCreationTime(time);
        assertEquals(time, ghost.getCreationTime());
    }

    @Test
    void getCreationTime() {

        long expectedTime = System.currentTimeMillis();
        ghost.setCreationTime(expectedTime);

        // Add a tolerance window of 100 milliseconds
        long actualTime = ghost.getCreationTime();
        assertTrue(Math.abs(actualTime - expectedTime) <= 100);
    }
}
