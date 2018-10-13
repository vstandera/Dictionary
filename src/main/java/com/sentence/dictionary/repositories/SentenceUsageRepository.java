package com.sentence.dictionary.repositories;

import com.sentence.dictionary.domain.SentenceUsage;
import org.springframework.data.repository.CrudRepository;

public interface SentenceUsageRepository extends CrudRepository<SentenceUsage,Long> {

    SentenceUsage findBySentence(String sentence);
}
