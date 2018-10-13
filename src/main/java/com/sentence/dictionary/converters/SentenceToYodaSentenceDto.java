package com.sentence.dictionary.converters;

import com.sentence.dictionary.converters.utils.SentenceUtil;
import com.sentence.dictionary.data.SentenceShortYodaDto;
import com.sentence.dictionary.domain.Sentence;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SentenceToYodaSentenceDto implements Converter<Sentence, SentenceShortYodaDto> {

    @Override
    public SentenceShortYodaDto convert(Sentence sentence) {
        final SentenceShortYodaDto sentenceShortDto = new SentenceShortYodaDto();
        sentenceShortDto.setEmbeddedSentence(SentenceUtil.constructYodaSentence(sentence));
        return sentenceShortDto;
    }
}
