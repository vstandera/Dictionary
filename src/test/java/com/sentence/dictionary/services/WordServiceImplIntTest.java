package com.sentence.dictionary.services;

import com.sentence.dictionary.data.WordDto;
import com.sentence.dictionary.domain.Word;
import com.sentence.dictionary.domain.enums.WordCategory;
import com.sentence.dictionary.repositories.WordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@ActiveProfiles("h2")
@SpringBootTest
@Transactional
public class WordServiceImplIntTest {

    @Autowired
    WordRepository wordRepository;

    @Autowired
    WordService wordService;


    @Test
    public void getAllWords() {
        Word word = Word.builder().word("Pavla").wordCategory(WordCategory.NOUN).build();
        Word word1 = Word.builder().word("je").wordCategory(WordCategory.VERB).build();
        Word word2 = Word.builder().word("hodna").wordCategory(WordCategory.ADJECTIVE).build();
        wordRepository.save(word);
        wordRepository.save(word1);
        wordRepository.save(word2);
        final List<WordDto> allWords = wordService.getAllWords();
        assertThat(allWords, hasSize(3));
        assertThat(allWords.get(0).getWord(),is("Pavla"));
        assertThat(allWords.get(0).getWordCategory(),is(WordCategory.NOUN.name()));
        assertThat(allWords.get(1).getWord(),is("je"));
        assertThat(allWords.get(1).getWordCategory(),is(WordCategory.VERB.name()));
        assertThat(allWords.get(2).getWord(),is("hodna"));
        assertThat(allWords.get(2).getWordCategory(),is(WordCategory.ADJECTIVE.name()));

    }

    @Test
    public void saveWord() {
        final WordDto word = wordService.saveWord(WordDto.builder().word("Pavla").wordCategory(WordCategory.NOUN.name()).build());
        assertThat(word.getWordCategory(),is(WordCategory.NOUN.name()));
        assertThat(word.getWord(), is("Pavla"));
    }

    @Test
    public void getWord() {
      wordService.saveWord(WordDto.builder().word("Pavla").wordCategory(WordCategory.NOUN.name()).build());
        final WordDto word = wordService.getWord("Pavla");
        assertThat(word.getWordCategory(),is(WordCategory.NOUN.name()));
        assertThat(word.getWord(), is("Pavla"));
    }
}