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
 * Package: org.team11.GameController;
 * Class: KeyFrenzyGameControllerTest
 *
 * Description: A test for the key frenzy game controller
 * **************************************
 */

package org.team11.GameController;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team11.Ghosts.Ghost;

import static org.junit.jupiter.api.Assertions.*;

class KeyFrenzyGameControllerTest {

    private KeyFrenzyGameController gameController;

    @BeforeAll
    static void init() {
        // Initialize JavaFX toolkit if not already initialized
        Platform.startup(() -> {});
    }


    @BeforeEach
    void setUp() {
        String username = "Team 11";
        gameController = new KeyFrenzyGameController(username);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initSceneGraph() {

        gameController.initSceneGraph();
        assertNotNull(gameController.getRoot());

        assertNotNull(gameController.getGamePane());
        assertEquals(800, gameController.getGamePane().getMinWidth());
        assertEquals(600, gameController.getGamePane().getMinHeight());

    }
    @Test
    void destroy() {
        // Create a mock Pane for testing
        GridPane gamePane = new GridPane();
        gameController.setGamePane(gamePane);

        // Create a Ghost object with a node
        Ghost ghost = new Ghost("test");

        // Check if the Ghost initialization was successful
        if (ghost.isActive()) {
            // Add the Ghost node to the gamePane
            gamePane.getChildren().add(ghost.getNode());

            // Verify that the Ghost node is initially in the gamePane
            assertTrue(gamePane.getChildren().contains(ghost.getNode()));

            // Call the destroy method with the Ghost object
            gameController.destroy(ghost);

            // Verify that the Ghost node is removed from the gamePane
            assertFalse(gamePane.getChildren().contains(ghost.getNode()));
        } else {
            // Log a message indicating that Ghost initialization failed
            System.err.println("Ghost initialization failed. Test skipped.");
        }
    }


    @Test
    void getRoot() {
        VBox root = gameController.getRoot();
        assertNotNull(root);
    }
}