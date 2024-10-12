package com.sample.qwords.repository;
import java.util.ArrayList;

public class WordList {

    private ArrayList<String> wordlist;


    public WordList() {

        this.wordlist = new ArrayList<String>();
        this.wordlist.add("animal");
        this.wordlist.add("bakery");
        this.wordlist.add("cracks");
        this.wordlist.add("drivel");
        this.wordlist.add("eatery");
        this.wordlist.add("frosty");
        this.wordlist.add("glazed");
    }

    public String getRandomWord() {

        return this.wordlist.get(0);
    }

}

