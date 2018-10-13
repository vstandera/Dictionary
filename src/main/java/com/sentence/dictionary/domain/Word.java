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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="WORD")
public class Word  implements Comparable{

    @GeneratedValue
    @Id
    private Long id;

    private String word;
    private WordCategory wordCategory;

    @Override
    public int compareTo(Object o) {
       Word WordObject =(Word) o;
        int response=0;
        switch (WordObject.getWordCategory()) {
            case NOUN:
                response = 1;
                break;
            case VERB:
                response = 2;
                break;
            case ADJECTIVE:
                response=3;
                break;
        }
        return response;
    }
}
