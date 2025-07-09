package com.sentence.dictionary.domain;

import com.sentence.dictionary.domain.enums.WordCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="WORD")
@Builder
public class Word extends BaseEntity implements Comparable<Word>{

    @Id
    @GeneratedValue
    private Long id;

    private String word;
    private WordCategory wordCategory;

    @Override
    public int compareTo(Word word) {
        if (word == null || this.wordCategory == null || word.getWordCategory() == null) {
            return 0;
        }
        return Integer.compare(this.wordCategory.ordinal(), word.getWordCategory().ordinal());
    }
}
