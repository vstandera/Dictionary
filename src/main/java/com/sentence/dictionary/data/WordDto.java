package com.sentence.dictionary.data;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * Word dto.
 */
public class WordDto {

    private String word;
    @ApiParam(value = "Can only have : NOUN or VERB or ADJECTIVE")
    private String wordCategory;
}
