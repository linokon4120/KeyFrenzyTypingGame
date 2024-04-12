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
 * Class: Dictionary
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

import java.io.*;
import java.util.*;
import java.util.Collections;

public class Dictionary {

    private Map<Integer, List<String>> wordsbylength = new HashMap<>();
    private static int key  = 2; //start off with 2-letter words

    public Dictionary() {
      loadFileintoMap("src/main/resources/Dictionary");
    }


    // Loads the first 100 lines from the dictionary file

    private void loadFileintoMap(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int length = line.length();
                wordsbylength.computeIfAbsent(length, k -> new ArrayList<>()).add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //check if this loaded correctly and call print map
       // printMap();

    }

    private void printMap(){
        System.out.println(wordsbylength.toString());
    }

    //get words based on the level and the number of words needed
    public List<String> getWords(int level, int numwords){
        List<String> words;
        Integer key = level + 1; //because the first level starts with two letters
        words = wordsbylength.get(key);
        // if the number of words needed at that level is more than what the dictionary has stored
        if (numwords >  words.size()){
            numwords = words.size(); //restricts it to the size of the number of words stored in the map

        }
        Collections.shuffle(words);

        return new ArrayList<>(words.subList(0, numwords));

    }
    public List<String> getWords(){ //assume level keeps increasing everytime getWords() is called and only 16 words are returned
        List<String> words;

        words = wordsbylength.get(this.key);
        // if the number of words needed at that level is more than what the dictionary has stored
        int numwords = 16; //hard coded to 16 words
        if (numwords >  words.size()){
            numwords = words.size();

        }
        //randomize the list
        Collections.shuffle(words);
        this.key ++; //because the level gets incremented each time the get words method is called

        return new ArrayList<>(words.subList(0, numwords));

    }






}

