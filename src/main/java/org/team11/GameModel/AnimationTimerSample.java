package org.team11.GameModel;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public abstract class AnimationTimerSample extends org.team11.GameView.KeyFrenzyView {
//    private static final KeyFrenzyModel t;
    private Label lbl;

    /** Manages the time loop*/
    private AnimationTimer animationTimer;

    /**Aids with pausing the game if necessary*/
    private boolean isPaused;

//    public AnimationTimerSample() {
//        super(theModel);
//    }

    /**
     * This is the "view" in the MVC design for the game Key Frenzy. A view class
     * does nothing more than initializes all nodes for the scene graph for this view.
     *
     * @param theModel the model of the game logic.
     */
    public AnimationTimerSample(KeyFrenzyModel theModel) {
        super(theModel);
    }

    public abstract void handle(long now);

    @Override
    public void start(Stage st) {
        UIinit(st);
        startAnimation();
    }

    private void UIinit(Stage st) {
        // Create a stackPane
        StackPane sp = new StackPane();

        lbl = new Label("Animation Sample");
        sp.getChildren().add(lbl);

        // Set up the scene, etc.
    }

    private void startAnimation() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                AnimationTimerSample.this.handle(now);
            }
        };
        animationTimer.start();
    }

    @Override
    public void stop() {
        animationTimer.stop();
    }


    
}
