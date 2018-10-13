package com.sentence.dictionary.services;

import com.sentence.dictionary.converters.SentenceToSentenceDto;
import com.sentence.dictionary.converters.SentenceToSentenceShortDto;
import com.sentence.dictionary.converters.SentenceToYodaSentenceDto;
import com.sentence.dictionary.converters.utils.SentenceUtil;
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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * This Service handle all in sentence.
 */
@Service
public class SentenceServiceImpl implements SentenceService {


    SentenceRepository sentenceRepository;
    WordRepository wordRepository;
    SentenceToSentenceDto sentenceToSentenceDto;
    SentenceUsageRepository sentenceUsageRepository;
    SentenceToSentenceShortDto sentenceToSentenceShortDto;
    SentenceToYodaSentenceDto sentenceToYodaSentenceDto;

    public SentenceServiceImpl(SentenceRepository sentenceRepository, WordRepository wordRepository,
                               SentenceToSentenceDto sentenceToSentenceDto, SentenceUsageRepository sentenceUsageRepository,
                               SentenceToSentenceShortDto sentenceToSentenceShortDto, SentenceToYodaSentenceDto sentenceToYodaSentenceDto) {
        this.sentenceRepository = sentenceRepository;
        this.wordRepository = wordRepository;
        this.sentenceToSentenceDto = sentenceToSentenceDto;
        this.sentenceUsageRepository = sentenceUsageRepository;
        this.sentenceToSentenceShortDto = sentenceToSentenceShortDto;
        this.sentenceToYodaSentenceDto = sentenceToYodaSentenceDto;
    }

    @Override
    public List<SentenceDto> getAllSentences() {
        return StreamSupport.stream(sentenceRepository.findAll().spliterator(), false).map(sentenceToSentenceDto::convert).collect(Collectors.toList());
    }

    /**
     * This method generated sentence from stored word in order NOUN,VERB,ADJECTIVE. And store sentence usage.
     *
     * @return SentenceDto -- the returned SentenceDto object that represent Sentence JSON.
     */
    @Override
    public SentenceDto generateSentence() {
//        Get all words from database.
        final List<Word> words = StreamSupport.stream(wordRepository.findAll().spliterator(), false).collect(Collectors.toList());
        final List<Word> nounWords = words.stream().filter(word -> word.getWordCategory().equals(WordCategory.NOUN)).collect(Collectors.toList());
        final List<Word> verbWords = words.stream().filter(word -> word.getWordCategory().equals(WordCategory.VERB)).collect(Collectors.toList());
        final List<Word> adjectiveWords = words.stream().filter(word -> word.getWordCategory().equals(WordCategory.ADJECTIVE)).collect(Collectors.toList());

        if (nounWords.size() == 0 || verbWords.size() == 0 || adjectiveWords.size() == 0) {
            throw new RuntimeException("There si not enough words to make sentence.");
        }

        final Sentence sentence = initSentence(nounWords, verbWords, adjectiveWords);
        addSentenceUsage(sentence);
        // Store sentence and return converted sentence.
        return sentenceToSentenceDto.convert(sentenceRepository.save(sentence));
    }

    /**
     * This method get sentence from database by id and increase viewCount.
     *
     * @param id Id sentence.
     * @return SentenceShortDto - the returned SentenceShortDto object that represent not full sentence JSON.
     */
    @Override
    public SentenceShortDto getSentence(Long id) {
        final Optional<Sentence> sentenceO = sentenceRepository.findById(id);
        if (sentenceO.isPresent()) {
            final Sentence sentence = sentenceO.get();
            sentence.setNumberOfView(sentence.getNumberOfView() + 1);
            return sentenceToSentenceShortDto.convert(sentenceRepository.save(sentence));
        } else {
            throw new RuntimeException("Sentence by Id was not found.");
        }
    }

    /**
     * Get sentence form database. The sentence will have Yoda order words - NOUN,ADJECTIVE,VERB.
     *
     * @param id sentence
     * @return SentenceShortYodaDto - the returned SentenceShortYodaDto object have insight just sentence String.
     */
    @Override
    public SentenceShortYodaDto getYodaSentence(Long id) {
        final Optional<Sentence> sentence = sentenceRepository.findById(id);
        if (sentence.isPresent()) {
            return sentenceToYodaSentenceDto.convert(sentence.get());
        } else {
            throw new RuntimeException("Sentence by Id was not found.");
        }
    }

    /**
     * Handle all in SentenceUsage entity.
     *
     * @param sentence
     */
    private void addSentenceUsage(Sentence sentence) {
        final String sentenceString = SentenceUtil.constructSentence(sentence);
        final SentenceUsage bySentence = sentenceUsageRepository.findBySentence(sentenceString);
        if (bySentence == null) {
            SentenceUsage sentenceUsage = new SentenceUsage();
            sentenceUsage.setSentence(sentenceString);
            sentenceUsage = sentenceUsageRepository.save(sentenceUsage);
            sentence.addSentenceUsage(sentenceUsage);
        } else {
            sentence.addSentenceUsage(bySentence);
        }
    }

    /**
     * Initialize sentence and generate random words to sentence. One NOUN, One ADJECTIVE, One VERB.
     *
     * @param nounWords
     * @param verbWords
     * @param adjectiveWords
     * @return
     */
    private Sentence initSentence(List<Word> nounWords, List<Word> verbWords, List<Word> adjectiveWords) {
        Random rand = new Random();
        int nounRandomWord = rand.nextInt(nounWords.size());
        final Sentence sentence = new Sentence();
        List<Word> wordsToSentence = new ArrayList();
        wordsToSentence.add(nounWords.get(nounRandomWord));
        int verbRandomWord = rand.nextInt(verbWords.size());
        wordsToSentence.add(verbWords.get(verbRandomWord));
        int adjectiveRandomWord = rand.nextInt(adjectiveWords.size());
        wordsToSentence.add(adjectiveWords.get(adjectiveRandomWord));
        sentence.setWords(wordsToSentence);
        sentence.setLocalDateTime(LocalDateTime.now());
        sentence.setNumberOfView(0);
        return sentence;
    }


}
