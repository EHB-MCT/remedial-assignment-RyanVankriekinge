[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/BhMy8Rjk)
# Game Librarian
## Set-up of the application
### Requirements
- Java 21 or higher
- Gradle

### Back-end server
#### Profiles
The project uses Spring profiles for different environments:
- `dev:` H2 database (Local memory) 
- `prod:` PostgreSQL (for production)
#### Running in development mode
To start the API with the dev profile and H2 database: <br>
`./gradlew bootRun --args='--spring.profiles.active=dev'
`<br>
or choose a profile in Intellij and run.
## References
1. **Creating .gitignore file**  
   Used [gitignore.io](https://www.toptal.com/developers/gitignore) to generate a `.gitignore` file for a Node.js project in .gitignore
2. **Creating Spring Boot structure**
   Used [Spring Initializr](https://start.spring.io/) to generate Spring Boot file structure in main > src > *
3. **Spring boot starters**
   Used [Baeldung - Web Starter](https://www.baeldung.com/spring-boot-starters#bd-Starter) to implement web starter in application.yml and build.gradle.kts
4. **Spring boot JPA**
   Used [Baeldung - Data JPA Starter](https://www.baeldung.com/spring-boot-starters#bd-JPA) to implement JPA in application.yml and build.gradle.kts
5. **Spring Boot service, repository, controller, and component**  
   Used [JavaGuides - Service, Repository, Controller, and Component in Spring Boot](https://www.javaguides.net/2025/03/service-repository-controller-and-component-in-spring-boot.html) to understand and implement annotations in a Spring Boot application in main > src > kotlin > *
6. **Creating Models, Services, Repositories, Controllers and HTTP requests with spring boot and H2 database**  
   Used [Github - Dev 4 herexamen Ryan Vankriekinge](https://github.com/EHB-MCT/dev4-herexamen-RyanVankriekinge) to create models, controllers, repositories, services and http requests in src > main > *