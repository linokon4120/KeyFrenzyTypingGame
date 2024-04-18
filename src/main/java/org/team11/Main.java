package org.team11;

import org.team11.GameView.WordDictionary;

public class Main {
    public static void main(String[] args) {
            WordDictionary wordDictionary = new WordDictionary();
            // Update the file path below with the correct path to your wordDictionary file

            System.out.println(wordDictionary.getWord());
            System.out.println(wordDictionary.getWord());
            System.out.println(wordDictionary.getWord());
            System.out.println(wordDictionary.getWord());
            System.out.println(wordDictionary.getWords(3,4));

    }
}

