package com.sentence.dictionary.converters;

import com.sentence.dictionary.data.SentenceDto;
import com.sentence.dictionary.domain.Sentence;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SentenceToSentenceDto implements Converter<Sentence, SentenceDto> {

    @Override
    public SentenceDto convert(Sentence sentence) {
        final SentenceDto sentenceDto = SentenceDto.builder().id(sentence.getId())
                .localDateTime(sentence.getLocalDateTime())
                .numberOfView(sentence.getNumberOfView())
                .ids(sentence.getSentenceUsage().getSentences().stream().map(sen -> sen.getId()).collect(Collectors.toList()))
                .words(sentence.getWords()).build();
        sentenceDto.setSentenceUsageCount(sentenceDto.getIds().size());
        return sentenceDto;
    }
}
