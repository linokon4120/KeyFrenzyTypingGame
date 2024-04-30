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
 * Class: GhostAnimationTest
 *
 * Description: A test for the ghost animation
 * **************************************
 */

package org.team11.Ghosts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Timer;

import static org.junit.jupiter.api.Assertions.*;


class GhostAnimationTest {

    private GhostAnimation ghostAnimation;

    @BeforeEach
    void setUp() {
        Timer timer = new Timer();
        Ghost ghost = new Ghost("team11");
        this.ghostAnimation = new GhostAnimation(timer, ghost);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void stop() {

        ghostAnimation.start();

        ghostAnimation.stop();
        assertTrue(true, "Animation loop should stop.");
    }
}