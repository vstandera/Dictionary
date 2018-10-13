package com.sentence.dictionary.controllers;

import com.sentence.dictionary.data.WordDto;
import com.sentence.dictionary.domain.enums.WordCategory;
import com.sentence.dictionary.services.WordService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;

public class WordControllerTest {

    WordController wordController;
    @Mock
    WordService wordService;

    MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        wordController = new WordController(wordService);
        mockMvc = MockMvcBuilders.standaloneSetup(wordController).build();
    }

    @Test
    public void getAllWords() throws Exception {
        when(wordService.getAllWords()).thenReturn(getListWords());

        mockMvc.perform(get("/words")).andExpect(status().isAccepted()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].word", is("Pavla")))
                .andExpect(jsonPath("$[0].wordCategory", is("NOUN")));
        verify(wordService, times(1)).getAllWords();
    }

    @Test
    public void saveWord() throws Exception {
        when(wordService.saveWord(any())).thenReturn(getWordDto());

        mockMvc.perform(put("/words/Pavla").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "        \"wordCategory\": \"NOUN\"\n" +
                "    }")).andExpect(status().isCreated()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.word", is("Pavla")));
        verify(wordService, times(1)).saveWord(any());

    }

    @Test
    public void getWord() throws Exception {

        when(wordService.getWord(any())).thenReturn(getWordDto());

        mockMvc.perform(get("/words/Pavla")).andExpect(status().isAccepted()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.word", is("Pavla")));
        verify(wordService, times(1)).getWord(any());
    }

    private List<WordDto> getListWords() {
        WordDto word = WordDto.builder().word("Pavla").wordCategory(WordCategory.NOUN.name()).build();
        WordDto word1 = WordDto.builder().word("je").wordCategory(WordCategory.VERB.name()).build();
        WordDto word2 = WordDto.builder().word("hodna").wordCategory(WordCategory.ADJECTIVE.name()).build();

        List<WordDto> words = new ArrayList<>();
        words.add(word);
        words.add(word1);
        words.add(word2);
        return words;
    }

    private WordDto getWordDto() {
        return WordDto.builder().word("Pavla").wordCategory(WordCategory.NOUN.name()).build();

    }
}