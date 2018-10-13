package com.sentence.dictionary.repositories;

import com.sentence.dictionary.domain.Word;
import org.springframework.data.repository.CrudRepository;

public interface WordRepository extends CrudRepository<Word, Long> {

    Word findFirstByWord(String word);
}
