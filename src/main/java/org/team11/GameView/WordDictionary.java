/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: YOUR NAME
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
package org.team11.GameView;

import org.team11.TypingMechanism.GuessStatus;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Collections;

public class WordDictionary {

    private Map<Integer, List<String>> wordsbylength = new HashMap<>();
    private static int key  = 2; //start off with 2-letter words
    private Set<String> currentWords;
    private List<String> dictionary;
    private int score;
    private Random rand;

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
        //check if this loaded correctly and call print map
        // printMap();

    }

    private void printMap(){
        System.out.println(wordsbylength.toString());
    }

    /**
     * Get words based on the level and the number of words needed
     */

    public List<String> getWords(int level, int numwords){
        List<String> words;
        Integer key = level + 1; //because the first level starts with two letters
        words = wordsbylength.get(key);
        // if the number of words needed at that level is more than what the dictionary has stored
        if (numwords >  words.size()){
            numwords = words.size(); //restricts it to the size of the number of words stored in the map

        }
        Collections.shuffle(words);
        int actualNumWords = Math.min(numwords, wordsbylength.size());
        List<String> selectedWords = new ArrayList<>(words.subList(0, actualNumWords));

        // Remove the selected words from the map to ensure they are not used again
        words.subList(0, actualNumWords).clear();

        return selectedWords;




    }
    public String getWord(){ //assume level keeps increasing everytime getWords is called and only 16 words are returned
//        List<String> words;
//
//        words = wordsbylength.get(this.key);
//        // if the number of words needed at that level is more than what the dictionary has stored
//        int numwords = 16; //hard coded to 16 words
//        if (numwords >  words.size()){
//            numwords = words.size();
//
//        }
//        //randomize the list
//        Collections.shuffle(words);
//        this.key ++; //because the level gets incremented each time the get words method is called
//
//        return new ArrayList<>(words.subList(0, numwords));
        String word;
        do {
            int num = rand.nextInt(dictionary.size());
            word = dictionary.get(num);
        } while (currentWords.contains(word));
        currentWords.add(word);
        return word;

    }

    /**
     * Makes an attempt to the type a word on the screen
     * @param text The guess made
     * @return Either correct, invalid word, or wrong
     */
    public GuessStatus guess(String text) {
        if (currentWords.contains(text)) {
            // If the guess is correct, increase the score and remove that word
            score++;
            currentWords.remove(text);
            return GuessStatus.CORRECT;
        }
        // If not in the dictionary
        return GuessStatus.INVALID_WORD;
    }

    public int getScore() {
        return score;
    }


}

