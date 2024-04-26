package org.team11.TypingMechanism;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WordDictionaryTest {

    @Test
    void getWord() {
        WordDictionary wordGenerator = new WordDictionary();
        Set<String> generatedWords = new HashSet<>();

        // Test for 16 consecutive levels
        for (int level = 1; level <= 7; level++) {
            String word = wordGenerator.getWord(level);
            assertNotNull(word);
            assertFalse(generatedWords.contains(word));
            generatedWords.add(word);
        }

        // Test for the case when level exceeds the maximum level
        String word = wordGenerator.getWord(4);
        assertNotNull(word);
        assertFalse(generatedWords.contains(word));
    }


}


