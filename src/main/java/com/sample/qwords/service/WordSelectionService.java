package com.sample.qwords.service;

import com.sample.qwords.repository.WordList;
import org.springframework.stereotype.Service;

@Service
public class WordSelectionService {

    private WordList wordlist;
    private String selectedWord;

    public WordSelectionService() {
        this.wordlist = new WordList();
        this.selectedWord = wordlist.getRandomWord();
    }

    public String getWord() {
        return this.selectedWord;
    }
    
    public String getNewRandomWord() {
        this.selectedWord = wordlist.getRandomWord();
        return this.selectedWord;
    }
}
