package com.example.dice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {
    @Test
    public void testDice() {
        Dice dice = new Dice();
        int result = dice.roll();
        assertTrue(result>= 1 && result <=6, "Le resultat est entre 1 et 6");
    }

}
