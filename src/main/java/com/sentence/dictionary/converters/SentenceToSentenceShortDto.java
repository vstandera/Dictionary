package com.sentence.dictionary.converters;

import com.sentence.dictionary.converters.utils.SentenceUtil;
import com.sentence.dictionary.data.SentenceShortDto;
import com.sentence.dictionary.domain.Sentence;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SentenceToSentenceShortDto implements Converter<Sentence, SentenceShortDto> {

    @Override
    public SentenceShortDto convert(Sentence sentence) {
        final SentenceShortDto sentenceShortDto = new SentenceShortDto();
        sentenceShortDto.setEmbeddedSentence(SentenceUtil.constructSentence(sentence), sentence.getNumberOfView());
        return sentenceShortDto;
    }
}
