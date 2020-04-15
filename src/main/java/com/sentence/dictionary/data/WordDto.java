package com.sentence.dictionary.data;

import io.swagger.annotations.ApiParam;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
/**
 * Word dto.
 */
public class WordDto implements Serializable {

    private String word;
    private String wordCategory;
}
