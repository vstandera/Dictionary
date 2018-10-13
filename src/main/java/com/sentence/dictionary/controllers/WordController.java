package com.sentence.dictionary.controllers;

import com.sentence.dictionary.data.WordDto;
import com.sentence.dictionary.services.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "This is controller for handle Words.")
@RestController
public class WordController {

    WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @ApiOperation(value = "Find all words in database.")
    @GetMapping("/words")
    public ResponseEntity<List<WordDto>> getAllWords() {
        return new ResponseEntity<>(wordService.getAllWords(), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Save word to database.")
    @PutMapping("/words/{word}")
    public ResponseEntity<WordDto> saveWord(@PathVariable("word") String word, @RequestBody WordDto wordDto) {
        return new ResponseEntity<>(wordService.saveWord(WordDto.builder().word(word).wordCategory(wordDto.getWordCategory()).build()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get word form database by word.value.")
    @GetMapping("/words/{word}")
    public ResponseEntity<WordDto> getWord(@PathVariable String word) {
        return new ResponseEntity<>(wordService.getWord(word), HttpStatus.ACCEPTED);
    }

}
