package com.example.dice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @PostConstruct
    public void registerWithDiscovery() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> serviceInfo = new HashMap<>();
        serviceInfo.put("name", "dice");
        serviceInfo.put("url", "http://localhost:8081");

        String discoveryServiceUrl = "http://localhost:8083/discovery/register";
        try {
            restTemplate.postForEntity(discoveryServiceUrl, serviceInfo, String.class);
            System.out.println("Service successfully registered with discovery.");
        } catch (Exception e) {
            System.err.println("Error registering with discovery service: " + e.getMessage());
        }
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
