
# Chatôp

Cette application est une api de backend pour l'application chatop

## Installation

Pour installer et exécuter ce projet, suivez les étapes ci-dessous :

## Prérequis

Avant de commencer l'installation, assurez-vous d'avoir les éléments suivants installés sur votre système :

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL Server](https://dev.mysql.com/downloads/installer/)

## Étapes

1. Clonez le dépôt sur votre machine locale en utilisant la commande suivante :

   ```shell
   git clone https://github.com/votre-nom-utilisateur/votre-depot.git
   ```

2. Accédez au répertoire du projet :

   ```shell
   cd votre-depot
   ```

3. Mettez à jour la configuration de la base de données MySQL dans le fichier `application.properties` situé dans le répertoire `src/main/resources`. Modifiez les propriétés `spring.datasource.url`, `spring.datasource.username` et `spring.datasource.password` en fonction de votre configuration de serveur MySQL.

4. Construisez le projet en utilisant Maven :

   ```shell
   mvn clean install
   ```

5. Exécutez l'application en utilisant Maven :

   ```shell
   mvn spring-boot:run
   ```

   Cette commande lancera l'application Spring Boot.

6. Une fois l'application en cours d'exécution, vous pouvez y accéder à l'adresse `http://localhost:3001` selon votre configuration dans votre navigateur web.

Voilà ! Vous avez réussi à installer et à lancer le projet sur votre machine.

Si vous rencontrez des problèmes lors du processus d'installation, veuillez consulter la documentation du projet ou demander de l'aide sur les canaux de support du projet.
## Documentation

La documentation de l'API est générée automatiquement à l'aide de Swagger UI. Vous pouvez accéder à la documentation de l'API en suivant les étapes ci-dessous :

1. Assurez-vous que le projet est en cours d'exécution sur votre machine locale.

2. Ouvrez votre navigateur web et accédez à l'URL suivante : `http://localhost:3001/swagger-ui.html` (ou remplacez `localhost:3001` par l'URL appropriée si vous avez configuré un autre port ou une autre adresse).

3. Une fois la page Swagger UI chargée, vous verrez une interface conviviale avec la liste des endpoints disponibles dans votre API.

4. Explorez les différentes catégories et endpoints pour voir les détails, les paramètres, les descriptions et les exemples de chaque endpoint.

5. Utilisez Swagger UI pour tester directement les endpoints en fournissant les paramètres requis et en soumettant les requêtes.

6. La documentation Swagger fournit également la possibilité de tester les endpoints en utilisant des exemples de requêtes pré-remplies.

Notez que la documentation Swagger est générée automatiquement à partir des annotations et des informations définies dans vos classes de contrôleurs API.
## Authors

[@SamouraiDuweb](https://github.com/SamouraiDuWeb)

