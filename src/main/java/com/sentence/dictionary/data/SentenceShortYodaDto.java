package com.sentence.dictionary.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Short yoda sentence SentenceShortYodaDto returned from service.
 */
@Getter
@Setter
@ToString
public class SentenceShortYodaDto {

    private EmbeddedSentence sentence;

    @Getter
    @Setter
    public class EmbeddedSentence{
        private String text;

    }
    public void setEmbeddedSentence(String text){
        final SentenceShortYodaDto.EmbeddedSentence embeddedSentence = new SentenceShortYodaDto.EmbeddedSentence();
        embeddedSentence.setText(text);
        this.sentence=embeddedSentence;
    }
}
