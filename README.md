# AI-Based Software Test Automation System



## Description

Le **Système d'automatisation de tests logiciels basé sur l'IA** est une solution innovante conçue pour aider les équipes de développement à automatiser la génération, l'exécution et l'analyse des tests logiciels. En utilisant des modèles d'intelligence artificielle avancés, le projet vise à réduire les erreurs humaines, optimiser la couverture des tests, et améliorer la productivité globale des développeurs.

---

## Problématique

Les équipes de développement passent beaucoup de temps à créer et exécuter manuellement des tests unitaires et fonctionnels, ce qui entraîne :
- Une couverture de test insuffisante.
- Des erreurs humaines potentielles.
- Une perte de temps et de productivité.
- Des coûts élevés liés à la maintenance du code.

---

## Solution Proposée

Ce projet propose un outil basé sur l'IA pour :
1. **Analyser le code source** et détecter les failles potentielles.
2. **Générer automatiquement des scénarios de tests** unitaires et fonctionnels.
3. **Exécuter les tests générés** et identifier les erreurs.
4. Proposer **des correctifs ou des ajustements** pour améliorer la qualité du code.

---

## Impact

- **Réduction des erreurs humaines** dans les tests.
- **Augmentation de la couverture** et de la qualité des tests.
- **Amélioration de la productivité** des développeurs.
- Réduction des **coûts liés à la maintenance**.

---

## Architecture

### Aperçu

Le système est basé sur une architecture modulaire qui inclut les éléments suivants :

1. **Interface utilisateur (Frontend)** :
   - Permet à l'utilisateur de soumettre un fichier ou un prompt pour analyse.
   - Intègre un workflow simple et intuitif.

2. **Service Discovery (Eureka)** :
   - Gère l'enregistrement et la découverte des microservices.

3. **API Gateway** :
   - Route les requêtes vers les microservices correspondants.

4. **Microservices** :
   - **Analyse du code** : Analyse le code source pour détecter les erreurs potentielles.
   - **Génération de tests** : Génère des scénarios de tests unitaires et fonctionnels.
   - **Exécution des tests** : Exécute les tests générés et retourne les résultats.
   - **Intégration de l'IA** : Utilise des modèles d'apprentissage automatique pour optimiser les tests et suggérer des correctifs.

---

## Diagramme d'Architecture

Voici un aperçu de l'architecture complète (généré dans Draw.io) :


![Architecture](https://github.com/user-attachments/assets/142bd87c-661c-45d1-b41f-5ef9ecaebeb0)


---

## Technologies Utilisées

- **Backend** :
  - Spring Boot
  - Eureka pour la découverte de services
  - API Gateway (Spring Cloud Gateway)
- **Frontend** :
  - Angular
  - Material Design
- **IA** :
  - Modèles comme Hugging Face (StarCoder) ou Google Gemini pour la génération et la complétion de code.
- **Outils** :
  - Docker (conteneurisation des microservices)
  - Postman (tests API)


---

## Installation et Configuration

### Prérequis

1. Java 17+
2. Node.js et Angular CLI
3. Docker
4. Clé API pour les services d'IA (Google Gemini)

### Étapes

1. Clonez ce dépôt :
   ```bash
   git clone https://github.com/votre-projet-repo.git
   cd votre-projet-repo

### PDF LATEX
[AITest.pdf](https://github.com/user-attachments/files/18247346/AITest.pdf)


## Simulation

https://github.com/user-attachments/assets/070f9513-778f-4c74-96d9-786dfbef0b8b









