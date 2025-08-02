package com.sample.qwords;

import com.sample.qwords.model.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class WordTest {

    private Word word;

    @BeforeEach
    public void setUp() {
        word = new Word("animal");
    }

    @Test
    public void testGetWord() {
        Assertions.assertEquals("animal", word.getWord());
    }

    @Test
    public void testContainsCharacterPresent() {
        Assertions.assertTrue(word.contains('a'));
        Assertions.assertTrue(word.contains('n'));
        Assertions.assertTrue(word.contains('i'));
        Assertions.assertTrue(word.contains('m'));
        Assertions.assertTrue(word.contains('l'));
    }

    @Test
    public void testContainsCharacterNotPresent() {
        Assertions.assertFalse(word.contains('x'));
        Assertions.assertFalse(word.contains('z'));
        Assertions.assertFalse(word.contains('b'));
    }

    @Test
    public void testIsCorrectWithCorrectGuess() {
        char[] correctGuess = {'a', 'n', 'i', 'm', 'a', 'l'};
        Assertions.assertTrue(word.isCorrect(correctGuess));
    }

    @Test
    public void testIsCorrectWithIncorrectGuess() {
        char[] incorrectGuess = {'a', 'n', 'i', 'm', 'a', 'x'};
        Assertions.assertFalse(word.isCorrect(incorrectGuess));
    }

    @Test
    public void testIsCorrectWithWrongLength() {
        char[] shortGuess = {'a', 'n', 'i'};
        Assertions.assertFalse(word.isCorrect(shortGuess));
        
        char[] longGuess = {'a', 'n', 'i', 'm', 'a', 'l', 'x'};
        Assertions.assertFalse(word.isCorrect(longGuess));
    }

    @Test
    public void testGetInfoWithCorrectGuess() {
        String result = word.getInfo("animal");
        Assertions.assertEquals("++++++", result);
    }

    @Test
    public void testGetInfoWithPartiallyCorrectGuess() {
        // "bakery" vs "animal" - 'a' is in word but wrong position, others not in word
        String result = word.getInfo("bakery");
        Assertions.assertEquals("?XXXXX", result);
    }

    @Test
    public void testGetInfoWithSomeCorrectPositions() {
        // "anxxxx" vs "animal" - first two positions correct, rest not in word
        String result = word.getInfo("anxxxx");
        Assertions.assertEquals("++XXXX", result);
    }

    @Test
    public void testGetInfoWithMixedResults() {
        // "lamina" vs "animal" - all letters present but different positions
        String result = word.getInfo("lamina");
        Assertions.assertEquals("??????", result);
    }

    @Test
    public void testGetInfoWithCompletelyWrongGuess() {
        String result = word.getInfo("bcdefg");
        Assertions.assertEquals("XXXXXX", result);
    }

    @Test
    public void testGetInfoWithEmptyGuess() {
        String result = word.getInfo("");
        Assertions.assertEquals("", result);
    }

    @Test
    public void testWordWithDifferentWord() {
        Word frostyWord = new Word("frosty");
        Assertions.assertEquals("frosty", frostyWord.getWord());
        Assertions.assertTrue(frostyWord.contains('f'));
        Assertions.assertTrue(frostyWord.contains('r'));
        Assertions.assertFalse(frostyWord.contains('a'));
        
        String result = frostyWord.getInfo("frosty");
        Assertions.assertEquals("++++++", result);
    }
}