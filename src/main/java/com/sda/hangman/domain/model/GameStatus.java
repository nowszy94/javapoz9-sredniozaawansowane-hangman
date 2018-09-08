package com.sda.hangman.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GameStatus {
    private String name;
    private String phrase;
    private Character[] phraseState;
    private Integer maxAttempts;
    private Integer successAttempts;
    private Integer failedAttempts;
    private List<Character> history;

    public GameStatus(String name, String phrase) {
        this(name, phrase, 5);
    }

    public GameStatus(String name, String phrase, Integer maxAttempts) {
        this.name = name;
        this.phrase = phrase;
        this.phraseState = new GameStatusHelper().preparePhraseState(phrase);
        this.maxAttempts = maxAttempts;
        this.successAttempts = 0;
        this.failedAttempts = 0;
        this.history = new ArrayList<>();
    }

    public boolean isGameFinished() {
        if (failedAttempts >= maxAttempts) {
            return true;
        }

        for (Character character : phraseState) {
            if (character == null) {
                return false;
            }
        }
        return true;
    }

    public int getTotalAttempts() {
        return successAttempts + failedAttempts;
    }

    public FinishedGameStatus getFinishedGameStatus() {
        if (!isGameFinished()) {
            return FinishedGameStatus.RUNNING;
        }
        if (maxAttempts <= failedAttempts) {
            return FinishedGameStatus.LOSE;
        }
        return FinishedGameStatus.WON;
    }

    public String getCurrentPhraseStateWithLeftAttempts() {
        return phraseStateAsString() + " " + leftAttemptsAsString();
    }

    private String leftAttemptsAsString() {
        int leftAttempts = maxAttempts - failedAttempts;
        return "(" + leftAttempts + ")";
    }

    private String phraseStateAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : phraseState) {
            if (c == null) {
                stringBuilder.append('_');
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public void incrementFailureCounter() {
        failedAttempts++;
    }

    public void incrementSuccessCounter() {
        successAttempts++;
    }

    public void updateHistory(char letter) {
        history.add(letter);
    }

    public boolean historyContains(char letter) {
        return history.contains(letter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Character[] getPhraseState() {
        return phraseState;
    }

    public void setPhraseState(Character[] phraseState) {
        this.phraseState = phraseState;
    }

    public Integer getSuccessAttempts() {
        return successAttempts;
    }

    public void setSuccessAttempts(Integer successAttempts) {
        this.successAttempts = successAttempts;
    }

    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(Integer failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public List<Character> getHistory() {
        return history;
    }

    public void setHistory(List<Character> history) {
        this.history = history;
    }

    public static class GameStatusHelper {
        public Character[] preparePhraseState(String phrase) {
            char[] chars = phrase.toCharArray();
            Character[] result = new Character[chars.length];
            for (int i = 0; i < chars.length; i++) {
                if (!Character.isLetter(chars[i])) {
                    result[i] = chars[i];
                }
            }
            return result;
        }
    }
}
