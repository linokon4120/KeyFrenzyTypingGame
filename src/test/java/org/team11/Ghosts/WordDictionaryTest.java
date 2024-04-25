package org.team11.Ghosts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.team11.TypingMechanism.WordDictionary;

class WordDictionaryTest {

    private WordDictionary wordDictionary;

    @Test
    void getWordwithlevel() {
        wordDictionary = new WordDictionary();
        for (int level = 1; level <= 5; level++) {
            String word = wordDictionary.getWord(level);
            Assertions.assertEquals(level + 1, word.length(),
                    "Word returned should have " + (level + 1) + " letters for level " + level + ".");

        }

    }

    @Test
    void loadFileTest(){

    }


}



