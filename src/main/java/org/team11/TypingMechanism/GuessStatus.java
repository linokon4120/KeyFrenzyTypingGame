package org.team11.TypingMechanism;

public enum GuessStatus {
    /**
     * If the word is in the current words list
     */
    CORRECT,

    /**
     * If the word is not in the dictionary
     */
    INVALID_WORD,

    /**
     * If the word is not in the current words list
     */
    WRONG,
}
