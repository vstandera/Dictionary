package com.sentence.dictionary.data;

import lombok.*;

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
@ToString
public class SentenceDto{
    private Long id;

    private List<WordDto> words = new ArrayList();
    private LocalDateTime localDateTime;
    private int numberOfView;
    private List<Long> ids= new ArrayList<>();
    private Integer sentenceUsageCount;
}
