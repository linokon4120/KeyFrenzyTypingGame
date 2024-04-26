package org.team11.TypingMechanism;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class WordDictionaryTest {

    private WordDictionary dictionary;

    @BeforeEach
    void setUp() {
        dictionary = new WordDictionary();
    }

    @Test
    void testGetWordReturnsCorrectLength() {
        int level = 1;
        String word = dictionary.getWord(level);
        assertEquals(level + 1, word.length(), "The word length should match the level + 1");
    }

    @Test
    void testGetCorrectLength() {
        int level = 2;
        String word = dictionary.getWord(level);
        assertEquals(level + 1, word.length(), "The word length should match the level + 2");


    }












}
