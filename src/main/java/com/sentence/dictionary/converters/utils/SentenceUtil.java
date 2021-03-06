package com.sentence.dictionary.converters.utils;

import com.sentence.dictionary.domain.Sentence;
import com.sentence.dictionary.domain.Word;
import com.sentence.dictionary.exceptions.NotEnoughWordsException;

import java.util.Collections;
import java.util.List;

/**
 * Sentence util.
 */
public class SentenceUtil {

    /**
     * Construct the sentence from words in order NOUN VERB ADJECTIVE.
     *
     * @param sentence String constructed sentence.
     * @return
     */
    public static String constructSentence(Sentence sentence) {
        final List<Word> words = getWords(sentence);
        return "" + words.get(Sentence.NOUN_NUMBER_IN_SENTENCE).getWord() + " " + words.get(Sentence.VERB_NUMBER_IN_SENTENCE).getWord()
                + " " + words.get(Sentence.ADJECTIVE_NUMBER_IN_SENTENCE).getWord();
    }

    /**
     * Construct the sentence from words in order NOUN ADJECTIVE VERB.
     *
     * @param sentence String constructed sentence.
     * @return
     */
    public static String constructYodaSentence(Sentence sentence) {
        final List<Word> words = getWords(sentence);
        return "" + words.get(Sentence.NOUN_NUMBER_IN_SENTENCE).getWord() + " " + words.get(Sentence.ADJECTIVE_NUMBER_IN_SENTENCE).getWord()
                + " " + words.get(Sentence.VERB_NUMBER_IN_SENTENCE).getWord();
    }

    private static List<Word> getWords(Sentence sentence) {
        if (sentence.getWords().size() != Sentence.DEFINE_NUMBER_WORDS) {
            throw new NotEnoughWordsException("There is not valid number of words.");
        }
        final List<Word> words = sentence.getWords();
        // Sort words NOUN VERB ADJECTIVE
        Collections.sort(words);
        return words;
    }
}
