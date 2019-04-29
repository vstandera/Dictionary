package com.sentence.dictionary.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotEnoughWordsException extends RuntimeException {

    private static final Logger log = LogManager.getLogger(NotEnoughWordsException.class);

    public NotEnoughWordsException(String message) {
        super(message);
        log.info(message);
    }
}
