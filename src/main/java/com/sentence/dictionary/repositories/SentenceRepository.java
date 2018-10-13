package com.sentence.dictionary.repositories;

import com.sentence.dictionary.domain.Sentence;
import org.springframework.data.repository.CrudRepository;

public interface SentenceRepository extends CrudRepository<Sentence,Long> {
}
