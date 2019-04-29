package com.sentence.dictionary.domain.enums;

import com.sentence.dictionary.exceptions.WordCategoryException;

import java.util.Arrays;


public enum WordCategory {

    NOUN,VERB,ADJECTIVE;


    public static WordCategory convertToCategory(String wordCategory) {
        if (wordCategory != null) {
            String upperCaseWord = wordCategory.toUpperCase();
            for (WordCategory word : values()) {
                if (word.name().equals(upperCaseWord)) {
                    return word;
                }
            }

        }
        throw new WordCategoryException("The word category does not match. " + wordCategory);
    }
}
