import javafx.application.Application;
import javafx.stage.Stage;
import org.team11.GameView.Ghost;

public class GhostTest extends Application {

    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        // Now you can run your test code here or invoke another method containin the test code
        runGhostTest();
    }

    private void runGhostTest() {
        // Your test code here
        Ghost ghost = new Ghost("Test", 80);
        // Set a creation time
        long creationTime = System.currentTimeMillis();
        ghost.setCreationTime(creationTime);

        // Get the creation time and compare with the expected value
        long retrievedTime = ghost.getCreationTime();

        if (retrievedTime == creationTime) {
            System.out.println("Creation time set and retrieved successfully!");
        } else {
            System.out.println("Error: Creation time mismatch!");
        }
    }
}
