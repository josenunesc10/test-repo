package com.sample.qwords;

import com.sample.qwords.service.WordSelectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

public class WordSelectionServiceTest {

    private WordSelectionService wordSelectionService;

    @BeforeEach
    public void setUp() {
        wordSelectionService = new WordSelectionService();
    }

    @Test
    public void testGetWordReturnsNotNull() {
        String word = wordSelectionService.getWord();
        Assertions.assertNotNull(word);
    }

    @Test
    public void testGetWordReturnsValidLength() {
        String word = wordSelectionService.getWord();
        Assertions.assertEquals(6, word.length(), "Word should be 6 characters long");
    }

    @Test
    public void testGetWordReturnsValidWord() {
        Set<String> expectedWords = Set.of("animal", "bakery", "cracks", "drivel", "eatery", "frosty", "glazed");
        String word = wordSelectionService.getWord();
        Assertions.assertTrue(expectedWords.contains(word), 
            "Word should be from the predefined list: " + word);
    }

    @Test
    public void testGetWordConsistency() {
        // The same service instance should return the same word
        String word1 = wordSelectionService.getWord();
        String word2 = wordSelectionService.getWord();
        Assertions.assertEquals(word1, word2, "Same service instance should return the same word");
    }

    @Test
    public void testGetNewRandomWord() {
        String originalWord = wordSelectionService.getWord();
        String newWord = wordSelectionService.getNewRandomWord();
        
        Assertions.assertNotNull(newWord);
        Assertions.assertEquals(6, newWord.length());
        
        // After getting a new random word, getWord() should return the new word
        String currentWord = wordSelectionService.getWord();
        Assertions.assertEquals(newWord, currentWord);
    }

    @Test
    public void testGetNewRandomWordChangesSelection() {
        Set<String> wordsGenerated = new HashSet<>();
        
        // Generate multiple new random words
        for (int i = 0; i < 20; i++) {
            String word = wordSelectionService.getNewRandomWord();
            wordsGenerated.add(word);
        }
        
        // We should get more than one unique word if it's truly random
        Assertions.assertTrue(wordsGenerated.size() > 1, 
            "getNewRandomWord should generate different words across multiple calls");
    }

    @Test
    public void testMultipleServiceInstances() {
        WordSelectionService service1 = new WordSelectionService();
        WordSelectionService service2 = new WordSelectionService();
        
        String word1 = service1.getWord();
        String word2 = service2.getWord();
        
        // Both should be valid words
        Set<String> expectedWords = Set.of("animal", "bakery", "cracks", "drivel", "eatery", "frosty", "glazed");
        Assertions.assertTrue(expectedWords.contains(word1));
        Assertions.assertTrue(expectedWords.contains(word2));
        
        // They might be different (due to randomness) but both should be valid
        Assertions.assertEquals(6, word1.length());
        Assertions.assertEquals(6, word2.length());
    }
}