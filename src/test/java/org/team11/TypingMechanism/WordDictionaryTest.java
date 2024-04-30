/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 4/20/2024
 * Time: 9:03 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.TypingMechanism;
 * Class: WordDictionaryTest
 *
 * Description: A test for the WordDictionaryTest class
 * **************************************
 */
package org.team11.TypingMechanism;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
