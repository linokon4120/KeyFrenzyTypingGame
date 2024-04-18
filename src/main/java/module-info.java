module csci205_final_project {
    requires java.base;
    requires java.desktop;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports org.team11.GameView;
    exports org.team11.GameController;


    opens org.team11.GameController to javafx.fxml;
    opens org.team11.GameView to javafx.fxml;

}