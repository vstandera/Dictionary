package com.sentence.dictionary.services;

import com.sentence.dictionary.data.SentenceDto;
import com.sentence.dictionary.data.SentenceShortDto;
import com.sentence.dictionary.data.SentenceShortYodaDto;
import com.sentence.dictionary.domain.Word;
import com.sentence.dictionary.domain.enums.WordCategory;
import com.sentence.dictionary.repositories.WordRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("h2")
@SpringBootTest
@Transactional
public class SentenceServiceImplIntTest {

    @Autowired
    WordRepository wordRepository;

    @Autowired
    SentenceService sentenceService;

    @Before
    public void setUp() throws Exception {
        Word word = Word.builder().word("Pavla").wordCategory(WordCategory.NOUN).build();
        Word word1 = Word.builder().word("je").wordCategory(WordCategory.VERB).build();
        Word word2 = Word.builder().word("hodna").wordCategory(WordCategory.ADJECTIVE).build();
        wordRepository.save(word);
        wordRepository.save(word1);
        wordRepository.save(word2);
        sentenceService.generateSentence();
    }

    @Test
    public void getAllSentences() {
        final List<SentenceDto> allSentences = sentenceService.getAllSentences();
        final List<Word> words1 = allSentences.get(0).getWords();
        assertThat(allSentences, hasSize(1));
        assertThat(words1,hasSize(3));
        assertThat(words1.get(0).getWordCategory(),is(WordCategory.NOUN));
        assertThat(words1.get(0).getWord(), is("Pavla"));
        assertThat(words1.get(1).getWordCategory(),is(WordCategory.VERB));
        assertThat(words1.get(1).getWord(), is("je"));
        assertThat(words1.get(2).getWordCategory(),is(WordCategory.ADJECTIVE));
        assertThat(words1.get(2).getWord(), is("hodna"));
    }

    @Test
    public void generateSentence() {
        final List<SentenceDto> allSentences = sentenceService.getAllSentences();
        final List<Word> words1 = allSentences.get(0).getWords();
        assertThat(allSentences, hasSize(1));
        assertThat(words1,hasSize(3));
        assertThat(words1.get(0).getWordCategory(),is(WordCategory.NOUN));
        assertThat(words1.get(0).getWord(), is("Pavla"));
        assertThat(words1.get(1).getWordCategory(),is(WordCategory.VERB));
        assertThat(words1.get(1).getWord(), is("je"));
        assertThat(words1.get(2).getWordCategory(),is(WordCategory.ADJECTIVE));
        assertThat(words1.get(2).getWord(), is("hodna"));
        assertThat(allSentences.get(0).getNumberOfView(), is(0));
        assertThat(allSentences.get(0).getSentenceUsageCount(), is(1));
        assertThat(allSentences.get(0).getIds(), hasSize(1));
        assertThat(allSentences.get(0).getIds().get(0), is(1L));
    }

    @Test
    public void getSentence() {
        final List<SentenceDto> allSentences = sentenceService.getAllSentences();
        final SentenceShortDto sentence = sentenceService.getSentence(allSentences.get(0).getId());
        final SentenceShortDto.EmbeddedSentence sentence1 = sentence.getSentence();
        assertThat(sentence1.getText(),is("Pavla je hodna"));
        assertThat(sentence1.getNumberOfView(), is(1));
        final SentenceShortDto.EmbeddedSentence sentence2 = sentenceService.getSentence(allSentences.get(0).getId()).getSentence();
        assertThat(sentence2.getText(),is("Pavla je hodna"));
        assertThat(sentence2.getNumberOfView(), is(2));
    }

    @Test
    public void getYodaSentence() {
        final List<SentenceDto> sentences = sentenceService.getAllSentences();
        final SentenceShortYodaDto.EmbeddedSentence yodaSentence = sentenceService.getYodaSentence(sentences.get(0).getId()).getSentence();
        assertThat(yodaSentence.getText(),is("Pavla hodna je"));

    }
}