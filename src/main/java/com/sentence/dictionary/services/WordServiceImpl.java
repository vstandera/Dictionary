package com.sentence.dictionary.services;

import com.sentence.dictionary.converters.WordDtoToWord;
import com.sentence.dictionary.converters.WordToWordDto;
import com.sentence.dictionary.data.WordDto;
import com.sentence.dictionary.repositories.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * This Service handle all in words.
 */
@Service
public class WordServiceImpl implements WordService {

    WordRepository wordRepository;
    WordToWordDto wordToWordDto;
    WordDtoToWord wordDtoToWord;


    public WordServiceImpl(WordRepository wordRepository, WordToWordDto wordToWordDto, WordDtoToWord wordDtoToWord) {
        this.wordRepository = wordRepository;
        this.wordToWordDto = wordToWordDto;
        this.wordDtoToWord = wordDtoToWord;
    }

    /**
     * Get all words from database.
     *
     * @return WordDto Dto to return from service.
     */
    @Override
    public List<WordDto> getAllWords() {
        return StreamSupport.stream(wordRepository.findAll().spliterator(), false)
                .map(wordToWordDto::convert).collect(Collectors.toList());
    }

    /**
     * Save Word to database.
     *
     * @param wordDto represent word from service.
     * @return WordDto stored to database.
     */
    @Override
    public WordDto saveWord(WordDto wordDto) {
        return wordToWordDto.convert(wordRepository.save(wordDtoToWord.convert(wordDto)));
    }

    /**
     * Get Word by word String.
     *
     * @param word String value.
     * @return WordDto Dto to return from service.
     */
    @Override
    public WordDto getWord(String word) {
        return wordToWordDto.convert(wordRepository.findFirstByWord(word));
    }

}
