package com.sda.hangman.domain.model;

import com.sda.hangman.domain.model.GameStatus.GameStatusHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

//@RunWith(Enclosed.class)
public class GameStatusTest {

    @Test
    public void isFinished_should_return_true_when_failureAttempts_equals_maxAttempts() {
        //given
        GameStatus gameStatus = new GameStatus("testowy-user", "Ala ma kota", 1);

        //when
        gameStatus.setFailedAttempts(1);

        //then
        Assert.assertTrue(gameStatus.isGameFinished());
    }

    @Test
    public void isFinished_should_return_false_when_maxAttempts_is_bigger_than_failureAttempts() {
        //given
        GameStatus gameStatus = new GameStatus("testowy-user", "Ala ma kota", 2);

        //when
        gameStatus.setFailedAttempts(1);

        //then
        Assert.assertFalse(gameStatus.isGameFinished());
    }

    @Test
    public void isFinished_should_return_true_when_all_letters_are_guessed() {
        //given
        GameStatus gameStatus = new GameStatus("testowy-user", "Ala ma kota", 2);

        //when
        gameStatus.setPhraseState("Ala ma kota".chars().mapToObj(c -> (char) c).toArray(Character[]::new));

        //then
        Assert.assertTrue(gameStatus.isGameFinished());
    }

    @Test
    public void getCurrentPhraseStateWithLeftAttempts_should_return_text_with_underscores_for_empty_phrase_state() {
        //given
        GameStatus gameStatus = new GameStatus(null, "Ala ma kota", 5);

        //when
        String state = gameStatus.getCurrentPhraseStateWithLeftAttempts();

        //then
        Assert.assertEquals("___ __ ____ (5)", state);
    }


    public static class GameStatusHelperTest {

        @Test
        public void should_return_array_with_null_values_for_one_word_phrase() {
            //given
            GameStatusHelper gameStatusHelper = new GameStatusHelper();

            //when
            Character[] phraseState = gameStatusHelper.preparePhraseState("Wielkopolska");

            //then
            assertThat(phraseState).allMatch((e) -> e == null);
            assertThat(phraseState).containsOnlyNulls();
//        for (int i = 0; i < phraseState.length; i++) {
//            Assert.assertEquals(null, phraseState[i]);
//        }
        }

        @Test
        public void should_return_array_with_null_and_special_chars_values_for_multi_words_phrase() {
            //given
            GameStatusHelper gameStatusHelper = new GameStatusHelper();

            //when
            Character[] phraseState = gameStatusHelper.preparePhraseState("Ala ma-kota");

            //then
            assertThat(phraseState).containsOnly(null, ' ', '-');
            Assert.assertEquals((Character) ' ', phraseState[3]);
            Assert.assertEquals((Character) '-', phraseState[6]);
        }
    }

}