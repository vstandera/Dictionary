package com.sentence.dictionary.controllers;

import com.sentence.dictionary.aspect.Loggable;
import com.sentence.dictionary.data.AuthenticationBean;
import com.sentence.dictionary.data.SentenceDto;
import com.sentence.dictionary.data.SentenceShortDto;
import com.sentence.dictionary.data.SentenceShortYodaDto;
import com.sentence.dictionary.exceptions.NotEnoughWordsException;
import com.sentence.dictionary.exceptions.SentenceDoesNotExist;
import com.sentence.dictionary.services.SentenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
@Api(value = "Dictionary sentences", tags = {
        "Sententence1" })
@SwaggerDefinition(tags = { @Tag(name = "Sententence1", description = "This is a controller for handle Sentences.") })
public class SentenceController {

    private SentenceService sentenceService;

    public SentenceController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @ApiOperation(value = "Get all sentences from database.")
    @GetMapping("/sentences")
    @Loggable
    public ResponseEntity<List<SentenceDto>> getAllSentences() {
        try {
            return new ResponseEntity<>(sentenceService.getAllSentences(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", "Server error.");
            return new ResponseEntity<>(value, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/basicauth")
    public AuthenticationBean helloWorldBean() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        return new AuthenticationBean("You are authenticated");
    }

    @ApiOperation(value = "Generate sentence from random stored words in order NOUN,VERB,ADJECTIVE.")
    @PostMapping("/sentences/generate")
    @RolesAllowed("ADMIN")
    @Loggable
    public ResponseEntity<SentenceDto> generateSentence() {
        try {
            return new ResponseEntity<>(sentenceService.generateSentence(), HttpStatus.CREATED);
        } catch (NotEnoughWordsException e) {
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
        } catch (SentenceDoesNotExist wordException) {
            wordException.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", wordException.getMessage());
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
        } catch (SentenceDoesNotExist wordException) {
            wordException.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", wordException.getMessage());
            return new ResponseEntity<>(value, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", "Server error.");
            return new ResponseEntity<>(value, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
