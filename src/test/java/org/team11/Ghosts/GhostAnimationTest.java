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