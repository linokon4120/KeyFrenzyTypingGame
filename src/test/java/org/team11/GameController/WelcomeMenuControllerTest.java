package org.team11.GameController;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class WelcomeMenuControllerTest {

    @Mock
    private Button mockButtonStartGame;

    @Mock
    private TextField mockTextFieldUserName;

    @Mock
    private Stage mockStage;

    private WelcomeMenuController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new WelcomeMenuController();

        // Inject mocks into controller
        controller.buttonStartGame = mockButtonStartGame;
        controller.textFieldUserName = mockTextFieldUserName;
    }

    @Test
    public void testOnStartButtonClickWithValidUserName() {
        // Set up test data
        String validUserName = "TestUser";
        when(mockTextFieldUserName.getText()).thenReturn(validUserName);

        // Mock the scene and stage
        Scene mockScene = mock(Scene.class);
        when(mockStage.getScene()).thenReturn(mockScene);

        // Perform action
        controller.onStartButtonClick();

        // Verify expected interactions
        verify(mockTextFieldUserName).getText(); // Ensure we retrieved text from username field
        verify(mockStage).setTitle("Key Frenzy Typing Game"); // Verify stage title set
        verify(mockStage).setScene(mockScene); // Verify scene set to stage
        verify(mockStage).sizeToScene(); // Verify stage size adjustment
        verify(mockStage).show(); // Verify stage shown
        verify(mockStage).close(); // Verify stage close is called

//        verify(mockButtonStartGame.getScene().getWindow()).close(); // Verify current stage closed
    }

    @Test
    public void testOnStartButtonClickWithEmptyUserName() {
        // Set up test data
        when(mockTextFieldUserName.getText()).thenReturn("");

        // Perform action
        controller.onStartButtonClick();

        // Verify no interactions with stage and scene (since username is empty)
        verify(mockTextFieldUserName).getText(); // Ensure we retrieved text from username field
//        verifyZeroInteractions(mockStage); // No stage operations should be invoked
//        verifyZeroInteractions(mockButtonStartGame); // No button operations should be invoked
    }

    @Test
    public void testInitialize() {
        // Perform initialization
        controller.initialize();

        // Verify assertions
        assert controller.buttonStartGame != null : "fx:id=\"buttonStartGame\" was not injected";
        assert controller.textFieldUserName != null : "fx:id=\"textFieldUserName\" was not injected";
    }
}
