package com.sda.hangman.infrastructure.memory;

import com.sda.hangman.domain.port.PhraseRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InMemoryPhraseRepository implements PhraseRepository {

    private List<String> phrases = Arrays.asList(
            "Ala ma kota",
            "Wielkopolska",
            "Architektura hexagonalna",
            "Programowanie w Java"
    );

    @Override
    public String getPhrase() {
        Random random = new Random();
        return phrases.get(random.nextInt(phrases.size()));
    }
}
