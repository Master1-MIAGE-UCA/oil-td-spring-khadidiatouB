package com.example.dice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dice")
public class DiceController {
    private final DiceService diceService;

    @Autowired
    public DiceController(DiceService diceService, DiceService diceService1) {
        this.diceService = diceService1;
    }

    // Lancer un seul dé
    @GetMapping("/rollDice")
    public List<Integer> rollDice() {
        return diceService.rollDices(1);
    }

    // Lancer X dés
    @GetMapping("/rollDices/{X}")
    public List<Integer> rollDices(@PathVariable int X) {
        return diceService.rollDices(X);
    }

    // Afficher l'historique des lancés
    @GetMapping("/diceLogs")
    public List<DiceRollLog> getDiceLogs() {
        return diceService.getAllLogs();
    }


}
