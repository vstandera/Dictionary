package com.sentence.dictionary.services;

import com.sentence.dictionary.converters.SentenceToSentenceDto;
import com.sentence.dictionary.converters.SentenceToSentenceShortDto;
import com.sentence.dictionary.converters.SentenceToYodaSentenceDto;
import com.sentence.dictionary.data.SentenceDto;
import com.sentence.dictionary.data.SentenceShortDto;
import com.sentence.dictionary.data.SentenceShortYodaDto;
import com.sentence.dictionary.domain.Sentence;
import com.sentence.dictionary.domain.SentenceUsage;
import com.sentence.dictionary.domain.Word;
import com.sentence.dictionary.domain.enums.WordCategory;
import com.sentence.dictionary.repositories.SentenceRepository;
import com.sentence.dictionary.repositories.SentenceUsageRepository;
import com.sentence.dictionary.repositories.WordRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

public class SentenceServiceImplTest {

    SentenceService sentenceService;

    @Mock
    private SentenceRepository sentenceRepository;

    @Mock
    private WordRepository wordRepository;

    @Mock
    private SentenceUsageRepository sentenceUsageRepository;

    @Mock
    private SentenceToSentenceDto sentenceToSentenceDto;

    @Mock
    private SentenceToSentenceShortDto sentenceToSentenceShortDto;

    @Mock
    private SentenceToYodaSentenceDto sentenceToYodaSentenceDto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sentenceService = new SentenceServiceImpl(sentenceRepository, wordRepository, sentenceToSentenceDto, sentenceUsageRepository, sentenceToSentenceShortDto, sentenceToYodaSentenceDto);
    }

    @Test
    public void generateSentence() {

        when(wordRepository.findAll()).thenReturn(getListWords());
        when(sentenceRepository.save(any())).thenReturn(getSavedSentence());
        when(sentenceUsageRepository.save(any())).thenReturn(getSentenceUsage());
        when(sentenceToSentenceDto.convert(any())).thenCallRealMethod();

        SentenceDto sen = sentenceService.generateSentence();
        assertEquals(sen.getWords().size(), getListWords().size());
        assertEquals(sen.getWords().get(1).getWord(), getListWords().get(1).getWord());
        assertEquals(sen.getSentenceUsageCount(), new Integer(1));

    }

    @Test
    public void getSentence() {
        when(sentenceRepository.findById(1L)).thenReturn(Optional.of(getSavedSentence()));
        when(sentenceToSentenceShortDto.convert(any())).thenCallRealMethod();
        when(sentenceRepository.save(any())).thenReturn(getSavedSentence());

        SentenceShortDto sentenceDto = sentenceService.getSentence(1L);
        assertEquals(sentenceDto.getSentence().getText(), "Pavla je hodna");
        assertEquals(sentenceDto.getSentence().getNumberOfView(), getSavedSentence().getNumberOfView());

    }

    @Test
    public void getYodaSentence() {
        when(sentenceRepository.findById(1L)).thenReturn(Optional.of(getSavedSentence()));
        when(sentenceToYodaSentenceDto.convert(any())).thenCallRealMethod();

        final SentenceShortYodaDto yodaSentence = sentenceService.getYodaSentence(1L);
        assertEquals(yodaSentence.getSentence().getText(), "Pavla hodna je");
    }

    private Sentence getSavedSentence() {
        final Sentence sentence = new Sentence();
        sentence.setLocalDateTime(LocalDateTime.now());
        sentence.setWords(getListWords());
        sentence.setId(1L);
        final SentenceUsage sentenceUsage = getSentenceUsage();
        sentenceUsage.setSentences(Collections.singletonList(sentence));
        sentence.setSentenceUsage(sentenceUsage);
        return sentence;
    }

    private SentenceUsage getSentenceUsage() {
        final SentenceUsage sentenceUsage = new SentenceUsage();
        sentenceUsage.setSentence("Pavla je hodna");
        sentenceUsage.setId(2L);
        return sentenceUsage;
    }

    private List<Word> getListWords() {
        Word word = Word.builder().id(1L).word("Pavla").wordCategory(WordCategory.NOUN).build();
        Word word1 = Word.builder().id(2L).word("je").wordCategory(WordCategory.VERB).build();
        Word word2 = Word.builder().id(3L).word("hodna").wordCategory(WordCategory.ADJECTIVE).build();

        List<Word> words = new ArrayList<>();
        words.add(word);
        words.add(word1);
        words.add(word2);
        return words;
    }


}