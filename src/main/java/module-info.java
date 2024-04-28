module csci205_final_project {
    requires java.base;
    requires java.desktop;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports org.team11.Ghosts;
    exports org.team11.GameController;



    opens org.team11.GameController to javafx.fxml;
    opens org.team11.Ghosts to javafx.fxml;
    exports org.team11.TypingMechanism;
    opens org.team11.TypingMechanism to javafx.fxml;

}