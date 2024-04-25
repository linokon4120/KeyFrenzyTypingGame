/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: YOUR NAME: Rahul Sibal
 * Section: YOUR SECTION
 * Date: 4/9/24
 * Time: 4:24 PM
 *
 * Project: csci205_final_project
 * Package: org.team11.GameView
 * Class: WordDictionary
 *
 * Description: this class reads the dictionary and puts it in a map with the
 * key = length of the words and the value are all the words of that length
 * this class supports a getword method.
 * the getword method returns a List<String> based on
 * the level of the game and the number of words desired in the List<String>
 * this method is also overidden so that the number of words in the List<String>
 * is always 16 and the level increments each time the method is called
 *
 * ****************************************
 */
package org.team11.TypingMechanism;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordDictionary {

    private Map<Integer, List<String>> wordsbylength = new HashMap<>();
    private static int key  = 2; //start off with 2-letter words
    private Set<String> currentWords;
    private List<String> dictionary;
    private int score;
    private Random rand;

    /**
     * The constructor for the WordDictionary class
     */
    public WordDictionary() {
        loadFileIntoMap("src/main/resources/Dictionary");
        score = 0;
        rand = new Random(System.currentTimeMillis());
        currentWords = new HashSet<>();
    }


    /**
     * Loads the first 100 lines from the dictionary file
     */

    private void loadFileIntoMap(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            dictionary = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                dictionary.add(line);
                int length = line.length();
                wordsbylength.computeIfAbsent(length, k -> new ArrayList<>()).add(line);
            }
        } catch (IOException e) {
            System.err.println("Error in loading the file");
            e.printStackTrace();
        }

    }


    /**
     * Get words based on the level and the number of words needed
     */



    public String getWord(int level){ //assume level keeps increasing everytime getWords is called and only 16 words are returned

        Integer key = level + 1; //because the first level starts with two letters
        List<String> words = wordsbylength.get(key);
        // Shuffle in random order
        Collections.shuffle(words);

        String word;
        do {
            word = words.get(rand.nextInt(wordsbylength.size()));
        } while (currentWords.contains(word));
        currentWords.add(word);
        return word;

    }





}

