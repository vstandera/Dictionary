package com.sentence.dictionary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SENTENCE")
public class Sentence {
    public static int DEFINE_NUMBER_WORDS = 3;
    public static int NOUN_NUMBER_IN_SENTENCE = 0;
    public static int VERB_NUMBER_IN_SENTENCE = 1;
    public static int ADJECTIVE_NUMBER_IN_SENTENCE = 2;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * List of words.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "word_sen_id")
    private List<Word> words = new ArrayList<>();
    private LocalDateTime localDateTime;
    private int numberOfView;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sentenceUsage_id")
    private SentenceUsage sentenceUsage;

    public Sentence addSentenceUsage(SentenceUsage sentenceUsage) {
        sentenceUsage.getSentences().add(this);
        this.setSentenceUsage(sentenceUsage);
        return this;
    }


}
