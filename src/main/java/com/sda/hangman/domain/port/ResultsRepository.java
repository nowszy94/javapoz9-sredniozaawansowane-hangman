package com.sda.hangman.domain.port;

import java.util.List;
import java.util.Map;

public interface ResultsRepository {
    Map<String, List<String>> getAllResults();
}
