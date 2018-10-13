package com.sentence.dictionary.controllers;

import com.sentence.dictionary.data.SentenceDto;
import com.sentence.dictionary.data.SentenceShortDto;
import com.sentence.dictionary.data.SentenceShortYodaDto;
import com.sentence.dictionary.services.SentenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "This is a controller for handle Sentences.")
public class SentenceController {

    SentenceService sentenceService;

    public SentenceController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @ApiOperation(value = "Get all sentences from database.")
    @GetMapping("/sentences")
    public ResponseEntity<List<SentenceDto>> getAllSentences() {
        try {
            return new ResponseEntity<>(sentenceService.getAllSentences(), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", e.getMessage());
            return new ResponseEntity<>(value, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", "Server error.");
            return new ResponseEntity<>(value, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Generate sentence from random stored words in order NOUN,VERB,ADJECTIVE.")
    @PostMapping("/sentences/generate")
    public ResponseEntity<SentenceDto> generateSentence() {
        try {
            return new ResponseEntity<>(sentenceService.generateSentence(), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", e.getMessage());
            return new ResponseEntity<>(value, HttpStatus.INSUFFICIENT_STORAGE);
        } catch (Exception e) {
            e.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", "Server error.");
            return new ResponseEntity<>(value, HttpStatus.INSUFFICIENT_STORAGE);
        }

    }

    @ApiOperation(value = "Get sentence from database by Id. And increase viewCount.")
    @GetMapping("/sentences/{sentenceID}")
    public ResponseEntity<SentenceShortDto> getSentence(@PathVariable(name = "sentenceID") Long id) {
        try {
            return new ResponseEntity<>(sentenceService.getSentence(id), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", e.getMessage());
            return new ResponseEntity<>(value, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", "Server error.");
            return new ResponseEntity<>(value, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get sentence form database. The sentence will have Yoda order words - NOUN,ADJECTIVE,VERB.")
    @GetMapping("/sentences/{sentenceID}/yodaTalk")
    public ResponseEntity<SentenceShortYodaDto> getYodaSentence(@PathVariable(name = "sentenceID") Long id) {
        try {
            return new ResponseEntity<>(sentenceService.getYodaSentence(id), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", e.getMessage());
            return new ResponseEntity<>(value, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", "Server error.");
            return new ResponseEntity<>(value, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}