package com.example.dice;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@NoArgsConstructor
@Component
@Data
public class Dice {
    private final Random random = new Random();

    public int roll()
    {
        return random.nextInt(6) + 1;
    }
}
