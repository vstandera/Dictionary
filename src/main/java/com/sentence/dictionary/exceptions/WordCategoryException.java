package com.sentence.dictionary.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordCategoryException extends RuntimeException {

    private static final Logger log = LogManager.getLogger(WordCategoryException.class);

    public WordCategoryException(String message) {
        super(message);
        log.info(message);
    }
}
