/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/8/2024
 * Time: 2:24 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameView
 * Class: GameGUIController
 *
 * Description:
 *
 * **************************************
 */
package org.team11.GameView;

import org.team11.GameModel.KeyFrenzyModel;

public class KeyFrenzyController {

    private final KeyFrenzyModel theModel;
    private final KeyFrenzyView theView;

    public KeyFrenzyController(KeyFrenzyModel theModel, KeyFrenzyView theView) {
        this.theModel = theModel;
        this.theView = theView;

        initBindings();
        initEventHandlers();
    }

    public void initBindings() {
    }

    public void initEventHandlers() {

    }

//    private void setupKeyEvents() {
//        theView.getScene().setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                handleEnterKey();
//            } else {
//                handleTyping(event.getText());
//            }
//        });
//    }
//
//    private void handleEnterKey() {
//        String typedWord = theModel.getCurrentWord();
//        if (!typedWord.isEmpty()) {
//            // Check if typed word matches any ghost's word
//            if (theView.checkWordMatch(typedWord)) {
//                theModel.incrementScore();
//                theView.updateScore(theModel.getScore());
//                theView.clearTypedWord();
//                theModel.generateNewWord();
//            }
//        }
//    }
//
//    private void handleTyping(String typedCharacter) {
//        theModel.updateCurrentWord(typedCharacter);
//        theView.updateTypedWord(theModel.getCurrentWord());
//    }
}