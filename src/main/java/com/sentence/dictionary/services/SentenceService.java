package com.sentence.dictionary.services;

import com.sentence.dictionary.data.SentenceDto;
import com.sentence.dictionary.data.SentenceShortDto;
import com.sentence.dictionary.data.SentenceShortYodaDto;

import java.util.List;

/**
 * This service handle all in sentence.
 */
public interface SentenceService {
    /**
     * This method generated sentence from stored word in order NOUN,VERB,ADJECTIVE. And store sentence usage.
     *
     * @return SentenceDto -- the returned SentenceDto object that represent Sentence JSON.
     */
    SentenceDto generateSentence();

    /**
     * This method get sentence from database by id and increase viewCount.
     *
     * @param id Id sentence.
     * @return SentenceShortDto - the returned SentenceShortDto object that represent not full sentence JSON.
     */
    SentenceShortDto getSentence(Long id);

    /**
     * Get sentence form database. The sentence will have Yoda order words - NOUN,ADJECTIVE,VERB.
     *
     * @param id sentence
     * @return SentenceShortYodaDto - the returned SentenceShortYodaDto object have insight just sentence String.
     */
    SentenceShortYodaDto getYodaSentence(Long id);

    /**
     * Get all sentence from database.
     *
     * @return List<SentenceDto> - is ful sentence Dto.
     */
    List<SentenceDto> getAllSentences();
}
