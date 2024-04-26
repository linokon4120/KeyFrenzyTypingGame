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
    void isActive() {
        assertTrue(ghost.isActive());
    }

    @Test
    void getNode() {
        assertNotNull(ghost.getNode());

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
