package com.sentence.dictionary.controllers;

import com.sentence.dictionary.data.WordDto;
import com.sentence.dictionary.exceptions.WordCategoryException;
import com.sentence.dictionary.messaging.RabbitService;
import com.sentence.dictionary.services.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Dictionary sentences", tags = {
        "Word1" })
@SwaggerDefinition(tags = { @Tag(name = "Word1", description = "This is controller for handle Words.") })
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
@RestController
public class WordController {

    private WordService wordService;

    private RabbitService rabbitService;

    public WordController(WordService wordService, RabbitService rabbitService) {
        this.wordService = wordService;
        this.rabbitService = rabbitService;
    }

    @ApiOperation(value = "Find all words in database.")
    @GetMapping("/words")
    public ResponseEntity<List<WordDto>> getAllWords() {
        return new ResponseEntity<>(wordService.getAllWords(), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Save word to database.")
    @PostMapping("words/{word}")
    public ResponseEntity<String> saveWord(@PathVariable("word") String word, @RequestBody WordDto wordDto) {
        try {
            rabbitService.sendMessage(WordDto.builder().word(word).wordCategory(wordDto.getWordCategory()).build());
            return new ResponseEntity("Message to RabbitMQ was send.", HttpStatus.CREATED);
        } catch (WordCategoryException wordCat) {
            wordCat.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", wordCat.getMessage());
            return new ResponseEntity<>(value, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
            value.add("error", "Server error.");
            return new ResponseEntity<>(value, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Get word form database by word.value.")
    @GetMapping("/words/{word}")
    public ResponseEntity<WordDto> getWord(@PathVariable String word) {
        return new ResponseEntity<>(wordService.getWord(word), HttpStatus.ACCEPTED);
    }

}
