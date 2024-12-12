[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/WCHp-cRl)
# Projet "Dice" - Gestion de lancés de dés avec Spring Boot

## Description
Le projet "Dice" est une application construite avec Spring Boot permettant de simuler des lancés de dés et de gérer un historique des résultats en base de données. Ce projet met en œuvre les concepts fondamentaux de Spring Boot, notamment l'injection de dépendances, les services RESTful, les entités JPA et les repositories.


## Étapes de réalisation

### 1. Création du projet Spring Boot
- Utilisez [Spring Initializr](https://start.spring.io/) pour créer le projet.
- Choisissez la dernière version de Spring Boot disponible (LTS).
- Optez pour **Maven** ou **Gradle** comme outil de gestion de dépendances.
- Ajoutez les dépendances nécessaires : **Spring Web**, **Spring Data JPA**, **H2 Database** .

### 2. Configuration du projet
- Configurez l'application pour qu'elle utilise le port **8081**.
- Donnez un nom (**dice**) au projet dans le fichier de configuration :
  - Utilisez **`application.properties`** ou **`application.yml`** selon votre préférence.

### 3. Création de la classe `Dice`
- Implémentez une classe représentant un dé avec les méthodes nécessaires pour effectuer un lancé.
- Marquez cette classe avec l'annotation `@Component` pour pouvoir l'injecter au besoin.

### 4. Création de l'entité `DiceRollLog`
- Modélisez une entité JPA `DiceRollLog` comprenant les champs suivants :
  - **`id`** : Identifiant unique.
  - **`diceCount`** : Nombre de dés lancés.
  - **`results`** : Liste ou chaîne des valeurs obtenues. Il existe de nombreuses façons de stocker des valeurs simples (simple String), certaines sont plus élégantes (@ElementCollection) que d'autres, vous pouvez choisir la solution qui vous conviendra.
  - **`timestamp`** : Horodatage du lancé.
- Utilisez des annotations JPA comme `@Entity`, `@Id`, `@GeneratedValue`, etc.

### 5. Création du `Repository`
- Implémentez une interface héritant de `JpaRepository<DiceRollLog, Long>` pour gérer les interactions avec la base de données.

### 6. Création du contrôleur REST pour lancer les dés
- Implémentez un contrôleur REST annoté avec `@RestController`.
- Ajoutez les endpoints suivants :
  - **`GET /rollDice`** : Lancer un seul dé.
  - **`GET /rollDices/{X}`** : Lancer X dés (X étant un paramètre dynamique).

### 7. Création du `Service`
- Créez un service marqué avec `@Service` contenant une méthode :
  - Prend en paramètre le nombre de dés à lancer.
  - Retourne les résultats des lancés au contrôleur.
  - Enregistre l’historique des lancés dans la base via le `Repository`.

### 8. Contrôleur pour afficher les historiques
- Ajoutez un autre contrôleur REST permettant d'afficher l'historique des lancés :
  - **`GET /diceLogs`** : Retourne tous les enregistrements de `DiceRollLog` au format JSON.

### 9. Tests et validation
- Démarrez l'application et testez les endpoints.
- Vérifiez les résultats dans la base de données et les réponses JSON.

### 10. (Bonus) Ajout de fonctionnalités avancées
- **Swagger** :
  - Ajoutez la dépendance Swagger/OpenAPI.
  - Configurez Swagger pour documenter vos endpoints.
  - Accédez à la documentation sur **`http://localhost:8081/swagger-ui.html`**.
- **Lombok** :
  - Utilisez Lombok pour simplifier les getters, setters et constructeurs de vos entités.

## Description de ce qui a été implementé
- **Classe Dice** :
  Cette classe représente un dé capable de générer un résultat aléatoire entre 1 et 6.
  - Attributs :
    - private final Random random : Utilisé pour générer des nombres aléatoires.
  - Méthodes :
    - roll() : Retourne un entier entre 1 et 6, simulant le lancer d'un dé.
- **Classe DiceRollLog** :
Cette classe modélise une entité représentant l'historique d'un ou plusieurs lancés de dés.
  - Annotations :
    - @Entity : Indique que cette classe est une entité JPA.
    - @Id : Spécifie la clé primaire de l'entité.
    - @GeneratedValue : Génère automatiquement une valeur unique pour l'identifiant.
    - @ElementCollection : Gère la collection de valeurs (les résultats des dés).
- **Interface DiceRepository**
Cette interface gère l'interaction avec la base de données pour l'entité DiceRollLog. Extends JpaRepository<DiceRollLog, Long> : Fournit des méthodes standards (CRUD) pour manipuler l'entité DiceRollLog.
- **Classe DiceService** :
  Cette classe encapsule la logique métier pour gérer les lancés de dés et leur enregistrement.
  - Attributs :
     - private final Dice dice : Instance de la classe Dice pour simuler les lancés.
     - private final DiceRepository repository : Interface pour interagir avec la base de données.
  - Méthodes :
    - rollDices(int count) :
      Génère count résultats de dés en utilisant la méthode roll de la classe Dice. Utilise un flux (Stream.generate) pour appeler la méthode roll de la classe Dice un nombre spécifié de fois (count).
      Sauvegarde les résultats et le nombre de dés dans l'historique grâce au DiceRepository.
      Retourne la liste des résultats.
    - getAllLogs() :
      Retourne tous les enregistrements de l'historique des lancés à partir de la base de données.
- **Classe DiceController** :
  Cette classe fournit des endpoints REST pour interagir avec l'application.

---

## Livrables
- Le code complet du projet, accessible via un dépôt GitHub.
- Un fichier `README.md` décrivant les étapes réalisées

## Technologies
- **Framework principal** : Spring Boot
- **Base de données** : H2 
- **Documentation API** : Swagger (bonus)
- **Simplification de code** : Lombok (bonus)
