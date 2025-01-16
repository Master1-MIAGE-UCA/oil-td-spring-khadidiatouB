package com.example.dice;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceServiceTest {

    @Mock
    private DiceRepository diceRepository;

    @InjectMocks
    private DiceService diceService;

    @Test
    public void testSaveDiceRoll() {

    }



}
