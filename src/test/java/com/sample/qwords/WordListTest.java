package com.sample.qwords;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.sample.qwords.repository.WordList;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

public class WordListTest {

    private WordList wordList;

    @BeforeEach
    public void setUp() {
        wordList = new WordList();
    }

    /**
     * Basic, simple test of the WordList.getRandomWord() method to make
     * sure the random word is not null.
     */
    @Test
    public void testGetRandomWordIsNotNull() {
        String randomWord = wordList.getRandomWord();
        Assertions.assertNotNull(randomWord);
    }

    /**
     * Create a unit test that tests getRandomWord in the WordList class in the
     * WordList.java file.
     * The test should include for checking the length of the word is 6 characters
     * in length.
     */
    @Test
    public void testGetRandomWordLength() {
        String randomWord = wordList.getRandomWord();
        Assertions.assertEquals(6, randomWord.length(), "Random word should be 6 characters long");
    }

    /**
     * Create a unit test to verify getRandomWord is returning random responses.
     */
    @Test
    public void testGetRandomWordReturnsRandomResponses() {
        Set<String> uniqueWords = new HashSet<>();
        
        // Call getRandomWord multiple times to check for randomness
        for (int i = 0; i < 50; i++) {
            String word = wordList.getRandomWord();
            uniqueWords.add(word);
        }
        
        // We should get more than one unique word if it's truly random
        // With 7 words in the list and 50 calls, we should get multiple unique words
        Assertions.assertTrue(uniqueWords.size() > 1, 
            "getRandomWord should return different words across multiple calls");
    }

    @Test
    public void testGetRandomWordReturnsValidWords() {
        Set<String> expectedWords = Set.of("animal", "bakery", "cracks", "drivel", "eatery", "frosty", "glazed");
        
        for (int i = 0; i < 20; i++) {
            String word = wordList.getRandomWord();
            Assertions.assertTrue(expectedWords.contains(word), 
                "Random word should be from the predefined list: " + word);
        }
    }

    @Test
    public void testGetWordCount() {
        Assertions.assertEquals(7, wordList.getWordCount(), "Word list should contain 7 words");
    }
}
