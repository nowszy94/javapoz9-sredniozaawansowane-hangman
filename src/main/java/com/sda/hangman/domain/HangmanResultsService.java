package com.sda.hangman.domain;

import com.sda.hangman.domain.port.ResultsRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HangmanResultsService {

    private ResultsRepository resultsRepository;

    public HangmanResultsService(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    public List<String> getResults() {
        Map<String, List<String>> allResults = resultsRepository.getAllResults();
        return allResults.entrySet()
                .stream()
                .map(e -> mapObjectFromMap(e))
                .collect(Collectors.toList());
    }

    private String mapObjectFromMap(Map.Entry<String, List<String>> e) {
        String name = e.getKey();
        String value = "1,1";
        return name + "," + value;
    }

}
