package com.sample.qwords;

import org.junit.jupiter.api.Test;
import com.sample.qwords.repository.WordList;
import org.junit.jupiter.api.Assertions;

public class WordListTest {

  /**
   * Basic, simple test of the WordList.getRandomWord() method to make
   * sure the random word is not null.
   */
  @Test
  public void testGetRandomWordIsNotNull() {
    WordList wordList = new WordList();
    String randomWord = wordList.getRandomWord();
    Assertions.assertNotNull(randomWord);
  }

  /*
   * Create a unit test that tests getRandomWord in the WordList class in the
   * WordList.java file.
   * The test should include for checking the length of the word is 6 characters
   * in length.
   */

  /*
   * Create a unit test to verify getRandomWord is returning random responses.
   */

}
