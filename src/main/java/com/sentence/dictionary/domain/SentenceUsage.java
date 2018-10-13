package com.sentence.dictionary.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Table;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent usage of same sentences.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="SENTENCE_USAGE")
public class SentenceUsage {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "sentenceUsage", cascade = CascadeType.ALL)
    private List<Sentence> sentences = new ArrayList<>();

    private String sentence;

}