package com.example.a2praktikosdarbas;

public class WordCounter {
    public static int countWords(String text) {
        String[] words = text.split("[\\s,\\.]");
        int wordCount = 0;
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount++;
            }
        }
        return wordCount;
    }

    public static int countPunctuation(String text) {
        int punctuationCount = 0;
        for (char c : text.toCharArray()) {
            if (Character.toString(c).matches("[\\p{Punct}]")) {
                punctuationCount++;
            }
        }
        return punctuationCount;
    }
    public static int countPunctuationAndSpaces(String text) {
        int punctuationAndSpacesCount = 0;
        for (char c : text.toCharArray()) {
            if (Character.toString(c).matches("[\\p{Punct}\\s]")) {
                punctuationAndSpacesCount++;
            }
        }
        return punctuationAndSpacesCount;
    }
}