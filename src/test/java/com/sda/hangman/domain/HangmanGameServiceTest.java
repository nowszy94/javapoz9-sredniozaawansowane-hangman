package com.sda.hangman.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HangmanGameServiceTest {

    private HangmanGameService hangmanGameService;

    @Before
    public void init() {
        this.hangmanGameService = new HangmanGameService();
    }

    @Test
    public void should_return_array_with_character_indexes() {
        //given

        //when
        List<Integer> result = hangmanGameService.performCharacter('a', "Anna");

        //then
        Assert.assertEquals(2, result.size());
        Assert.assertEquals((Integer) 0, result.get(0));
        Assert.assertEquals((Integer) 3, result.get(1));
    }

    @Test
    public void should_return_empty_array_for_no_existing_charcter_in_phrase() {
        //given

        //when
        List<Integer> result = hangmanGameService.performCharacter('z', "ala ma kota");

        //then
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void should_retrun_array_with_character_indexes_from_phrase_containing_white_spaces() {
        //given

        //when
        List<Integer> result = hangmanGameService.performCharacter('a', "Ala ma kota");

        //then
        Assert.assertEquals(4, result.size());
        Assert.assertEquals((Integer) 0, result.get(0));
        Assert.assertEquals((Integer) 2, result.get(1));
        Assert.assertEquals((Integer) 5, result.get(2));
        Assert.assertEquals((Integer) 10, result.get(3));
    }
}
