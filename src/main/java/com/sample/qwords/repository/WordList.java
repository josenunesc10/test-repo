package com.sample.qwords.repository;
import java.util.ArrayList;
import java.util.Random;

public class WordList {

    private ArrayList<String> wordlist;
    private Random random;

    public WordList() {
        this.wordlist = new ArrayList<String>();
        this.wordlist.add("animal");
        this.wordlist.add("bakery");
        this.wordlist.add("cracks");
        this.wordlist.add("drivel");
        this.wordlist.add("eatery");
        this.wordlist.add("frosty");
        this.wordlist.add("glazed");
        this.random = new Random();
    }

    public String getRandomWord() {
        if (wordlist.isEmpty()) {
            throw new IllegalStateException("Word list is empty");
        }
        int randomIndex = random.nextInt(wordlist.size());
        return this.wordlist.get(randomIndex);
    }

    public int getWordCount() {
        return wordlist.size();
    }
}

