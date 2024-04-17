module csci205_final_project {
    requires java.base;
    requires java.desktop;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports org.team11.GameView;
    exports org.team11.GameModel;


    opens org.team11 to javafx.fxml;

}