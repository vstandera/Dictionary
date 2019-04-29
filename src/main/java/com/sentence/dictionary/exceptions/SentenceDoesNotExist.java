package com.sentence.dictionary.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceDoesNotExist extends RuntimeException {

    private static final Logger log = LogManager.getLogger(SentenceDoesNotExist.class);

    public SentenceDoesNotExist(String message) {
        super(message);
        log.info(message);
    }
}
