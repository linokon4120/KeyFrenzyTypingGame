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
 * Description:
 *
 * ****************************************
 */
package org.team11.GameView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Dictionary {
    private List<String> words = new ArrayList<>();

    // Loads the first 100 lines from the dictionary file
    public void loadFirst100Words(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            words = reader.lines().limit(100).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Groups the first 100 words by their length and prints them
    public void groupAndPrintFirst100Words() {
        Map<Integer, List<String>> groupedWords = words.stream()
                .collect(Collectors.groupingBy(String::length));

        // Print words with 2 letters
        System.out.println("Words with 2 letters are: " +
                Optional.ofNullable(groupedWords.get(2)).orElse(Collections.emptyList()));

        // Print words with 3 letters
        System.out.println("Words with 3 letters are: " +
                Optional.ofNullable(groupedWords.get(3)).orElse(Collections.emptyList()));

        // Print words with 4 letters
        System.out.println("Words with 4 letters are: " +
                Optional.ofNullable(groupedWords.get(4)).orElse(Collections.emptyList()));

        // Print words with at least 5 letters
        List<String> wordsWithAtLeastFiveLetters = groupedWords.entrySet().stream()
                .filter(entry -> entry.getKey() >= 5)
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
        System.out.println("Words with at least 5 letters are: " + wordsWithAtLeastFiveLetters);
    }

    // Example usage
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        // Update the file path below with the correct path to your dictionary file
        String dictionaryFilePath = "src/main/resources/Dictionary"; // Make sure to include the extension if the file has one
        dictionary.loadFirst100Words(dictionaryFilePath);
        dictionary.groupAndPrintFirst100Words();
    }
}

