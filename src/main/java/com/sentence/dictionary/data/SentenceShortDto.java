package com.sentence.dictionary.data;


import lombok.*;


/**
 * Not full  sentence Dto returned from service.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SentenceShortDto {

    private EmbeddedSentence sentence;

    @Getter
    @Setter
    public class EmbeddedSentence{
        private String text;
        private int numberOfView;
    }

    public void setEmbeddedSentence(String text, int numberOfView){
        final EmbeddedSentence embeddedSentence = new EmbeddedSentence();
        embeddedSentence.setNumberOfView(numberOfView);
        embeddedSentence.setText(text);
        this.sentence=embeddedSentence;
    }

}
