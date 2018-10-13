package com.sentence.dictionary.data;

import com.sentence.dictionary.domain.Word;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Main SentenceDto returned from service.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SentenceDto{
    private Long id;

    private List<Word> words = new ArrayList<>();
    private LocalDateTime localDateTime;
    private int numberOfView;
    private List<Long> ids= new ArrayList<>();
    private Integer sentenceUsageCount;
}
