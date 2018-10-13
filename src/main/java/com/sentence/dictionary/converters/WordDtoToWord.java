package com.sentence.dictionary.converters;

import com.sentence.dictionary.data.WordDto;
import com.sentence.dictionary.domain.Word;
import com.sentence.dictionary.domain.enums.WordCategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WordDtoToWord implements Converter<WordDto, Word> {
    @Override
    public Word convert(WordDto wordDto) {
        return Word.builder().word(wordDto.getWord()).wordCategory(WordCategory.convertToCategory(wordDto.getWordCategory())).build();
    }
}
