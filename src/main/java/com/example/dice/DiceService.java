package com.example.dice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DiceService {

    private final Dice dice;
    private final DiceRepository repository;

    @Autowired
    public DiceService(Dice dice, DiceRepository repository) {
        this.dice = dice;
        this.repository = repository;
    }

    // Lancer les dés et enregistrer l'historique
    public List<Integer> rollDices(int count) {
        // Générer une liste avec le nombre de lancers spécifié
        List<Integer> results = Stream.generate(dice::roll)
                .limit(count)
                .collect(Collectors.toList());

        // Sauvegarder l'historique du lancé
        DiceRollLog log = new DiceRollLog(count, results, LocalDateTime.now());
        repository.save(log);

        return results;
    }

    // Retourner l'historique des lancés
    public List<DiceRollLog> getAllLogs() {
        return repository.findAll();
    }

}
