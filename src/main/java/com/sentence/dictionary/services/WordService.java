package com.sentence.dictionary.services;

import com.sentence.dictionary.data.WordDto;

import java.util.List;


/**
 * This service handl all in word.
 */
public interface WordService {
    /**
     * Get all words from database.
     *
     * @return WordDto Dto to return from service.
     */
    List<WordDto> getAllWords();

    /**
     * Save Word to database.
     *
     * @param wordDto represent word from service.
     * @return WordDto stored to database.
     */
    WordDto saveWord(WordDto wordDto);

    /**
     * Get Word by word String.
     *
     * @param word String value.
     * @return WordDto Dto to return from service.
     */
    WordDto getWord(String word);
}
