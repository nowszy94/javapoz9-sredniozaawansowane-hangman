package com.sda.hangman.domain.model;

import com.sda.hangman.domain.model.GameStatus.GameStatusHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@RunWith(Enclosed.class)
public class GameStatusTest {

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