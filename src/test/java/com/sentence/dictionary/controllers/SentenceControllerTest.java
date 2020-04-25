package com.sentence.dictionary.controllers;

import com.sentence.dictionary.converters.WordToWordDto;
import com.sentence.dictionary.data.SentenceDto;
import com.sentence.dictionary.data.SentenceShortDto;
import com.sentence.dictionary.data.SentenceShortYodaDto;
import com.sentence.dictionary.data.WordDto;
import com.sentence.dictionary.domain.Word;
import com.sentence.dictionary.domain.enums.WordCategory;
import com.sentence.dictionary.services.SentenceService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

public class SentenceControllerTest {

    private SentenceController sentenceController;

    private MockMvc mockMvc;

    @Mock
    private SentenceService sentenceService;

    private static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sentenceController = new SentenceController(sentenceService);
        mockMvc = MockMvcBuilders.standaloneSetup(sentenceController).build();
    }

    @Test
    public void getAllSentences() throws Exception {
        SentenceDto sentenceDto = getSentenceDto();
        when(sentenceService.getAllSentences()).thenReturn(Collections.singletonList(sentenceDto));

        mockMvc.perform(get("/sentences")).andExpect(status().isAccepted()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].numberOfView", is(2)))
                .andExpect(jsonPath("$[0].words", hasSize(3)))
                .andExpect(jsonPath("$[0].words[0].word", is("Pavla")));
        final List<SentenceDto> sentenceDto2 = verify(sentenceService, times(1)).getAllSentences();
    }

    @Test
    public void generateSentence() throws Exception {
        SentenceDto sentenceDto1 = getSentenceDto();
        when(sentenceService.generateSentence()).thenReturn(sentenceDto1);

        mockMvc.perform(post("/sentences/generate")).andExpect(status().isCreated()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.numberOfView", is(2)))
                .andExpect(jsonPath("$.words", hasSize(3)));
        final SentenceDto sentenceDto = verify(sentenceService, times(1)).generateSentence();
    }

    private SentenceDto getSentenceDto() {
        WordToWordDto wordToWordDto = new WordToWordDto();
        Word word = Word.builder().id(1L).word("Pavla").wordCategory(WordCategory.NOUN).build();
        Word word1 = Word.builder().id(2L).word("je").wordCategory(WordCategory.VERB).build();
        Word word2 = Word.builder().id(3L).word("hodna").wordCategory(WordCategory.ADJECTIVE).build();

        List<Word> words = new ArrayList<>();
        words.add(word);
        words.add(word1);
        words.add(word2);
        List<WordDto> wordsDto = words.stream().map(wordToWordDto::convert).collect(Collectors.toList());
        return SentenceDto.builder().id(22L).numberOfView(2).localDateTime(LocalDateTime.now()).words(wordsDto).ids(Collections.singletonList(1L)).sentenceUsageCount(1).build();
    }

    private SentenceShortDto getSentenceShortDto() {
        final SentenceShortDto sentenceShortDto = new SentenceShortDto();
        sentenceShortDto.setEmbeddedSentence("Pavla je hodna", 2);
        return sentenceShortDto;
    }

    @Test
    public void getSentence() throws Exception {

        when(sentenceService.getSentence(22L)).thenReturn(getSentenceShortDto());

        mockMvc.perform(get("/sentences/22")).andExpect(status().isAccepted()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.sentence.text", is("Pavla je hodna")));
        verify(sentenceService, times(1)).getSentence(anyLong());

    }

    @Test
    public void getYodaSentence() throws Exception {

        when(sentenceService.getYodaSentence(22L)).thenReturn(getSentenceYodaShortDto());

        mockMvc.perform(get("/sentences/22/yodaTalk")).andExpect(status().isAccepted()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.sentence.text", is("Pavla hodna je")));
        verify(sentenceService, times(1)).getYodaSentence(anyLong());
    }

    private SentenceShortYodaDto getSentenceYodaShortDto() {
        final SentenceShortYodaDto sentenceShortYodaDto = new SentenceShortYodaDto();
        sentenceShortYodaDto.setEmbeddedSentence("Pavla hodna je");
        return sentenceShortYodaDto;
    }
}