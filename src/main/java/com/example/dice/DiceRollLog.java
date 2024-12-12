package com.example.dice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class DiceRollLog {

    @Id
    @GeneratedValue
    private Long id;
    private int diceCount;
    @ElementCollection
    private List<Integer> results;
    private LocalDateTime timestamp;

    public DiceRollLog(int diceCount, List<Integer> results, LocalDateTime timestamp) {
        this.diceCount = diceCount;
        this.results = results;
        this.timestamp = timestamp;
    }


}
