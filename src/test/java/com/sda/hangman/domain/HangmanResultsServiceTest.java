package com.sda.hangman.domain;

import com.sda.hangman.domain.port.ResultsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HangmanResultsServiceTest {

    @Test
    public void getAllResults_should_work() {
        //given
        ResultsRepository resultRepositoryMock = Mockito.mock(ResultsRepository.class);
        Map<String, List<String>> map = new HashMap<String, List<String>>() {{
            put("Szymon", Arrays.asList("5,2,W", "3,5,L"));
            put("Anna", Arrays.asList("5,0,W", "5,5,W"));
        }};

        Map<String, List<String>> map2 = new HashMap<>();
        map2.put("Szymon", Arrays.asList("5,2,W", "3,5,L"));
        map2.put("Anna", Arrays.asList("5,0,W", "5,5,W"));

        Mockito.when(resultRepositoryMock.getAllResults()).thenReturn(map);

        HangmanResultsService hangmanResultsService = new HangmanResultsService(resultRepositoryMock);

        //when
        List<String> results = hangmanResultsService.getResults();

        //then
        Assert.assertEquals(2, results.size());

    }

}
