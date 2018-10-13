package com.sentence.dictionary.converters;

import com.sentence.dictionary.data.WordDto;
import com.sentence.dictionary.domain.Word;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WordToWordDto implements Converter<Word, WordDto> {

    @Override
    public WordDto convert(Word word) {
        return WordDto.builder().word(word.getWord())
                .wordCategory(word.getWordCategory().name())
                .build();
    }
}
