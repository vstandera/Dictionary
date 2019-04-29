package com.sentence.dictionary.listener;

import com.sentence.dictionary.data.WordDto;
import com.sentence.dictionary.services.WordService;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class WordMessageListener {

    private static final Logger log = LogManager.getLogger(WordMessageListener.class);
    private WordService wordService;

    public WordMessageListener(WordService wordService) {
        this.wordService = wordService;
    }

    public void receiveMessage(WordDto wordDto){
        log.info("Input message to WordListener {}", wordDto);
        wordService.saveWord(wordDto);
    }
}
