[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/BhMy8Rjk)
# Game Librarian
## Back-end server set-up
### Requirements
- Java 21 or higher
- Gradle
### Profiles
The project uses Spring profiles for different environments:
- `dev:` H2 database (Local memory) 
- `pro:` PostgreSQL (for production)
### Running in development mode
To start the API with the dev profile and H2 database: <br>
`./gradlew bootRun --args='--spring.profiles.active=dev'
`<br>
or create a dev profile in Intellij and run.
## Set up PostgreSQL database with Podman
How to set up the PostgreSQL instance using Podman on Windows (via WSL).

### 1. Install Podman
Follow the official installation guide: [Podman Installation Guide](https://podman.io/getting-started/installation)

### 2. Install WSL
Ensure Windows Subsystem for Linux (WSL) is installed and configured. The cmd will notify you if this is not done properly.

### 3. Initialize Virtual Machine
Run: `podman machine init --cpus 2 --memory 4096 --disk-size 20`

### 4. Start Virtual Machine
Run: `podman machine start`

### 5. Create Volume for Data
Run: `podman volume create gamelibrarian-data`

### 6. Start PostgreSQL
Run:  
`podman run -d --name gamelibrarian-pro -e POSTGRES_USER=app -e POSTGRES_PASSWORD=app_pw -e POSTGRES_DB=gamelibrarian -p 5432:5432 -v gamelibrarian-data:/var/lib/postgresql/data:Z docker.io/postgres:16`

### 7. Check Database Instance
Run: `podman ps`

### 8. Load Initial Data (One-Time)
Run:  
`podman cp data_postgres.sql gamelibrarian-pro:/tmp/data_postgres.sql`  
`podman exec -it gamelibrarian-pro psql -U app -d gamelibrarian -v ON_ERROR_STOP=1 -f /tmp/data_postgres.sql`

### 9. Run in production mode
To run the server in production mode, set-up a 'pro' profile in IntelliJ and run.

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
7. **Using annotations to define relationships between entities**  
   Used [Baeldung - JPA Hibernate associations](https://www.baeldung.com/jpa-hibernate-associations#bd-unidirectional-associations) to define relationships in in src > main > kotlin > be.ehb.gamelibrarian > model > *
8. **Enable H2 console**  
   Used [StackOverflow - Configuring H2 database via Yaml Spring Boot](https://stackoverflow.com/questions/56463145/configuring-h2-database-via-yaml-spring-boot) to export data from memory.
9. **Save H2 data to file**  
   Used [ChatGPT - Save H2 Database data](https://chatgpt.com/share/68a1d9c7-6050-8002-b783-3decfbe7bd6b) to create data.sql file from H2 data.
10. **Incrementing the popularity property**  
   Used [ChatGPT - Increment field with JPA](https://chatgpt.com/share/68a1fd0f-4d94-8002-93ee-a77b7a2a08cd) to increment the popularity property from Boardgame when a new Lending is created in src > main > kotlin > be.ehb.gamelibrarian > service > LendingService line 17-29
11. **Converting H2 file to PostgreSQL**  
   Used [Traccar - How I migrated from h2 to postgres](https://www.traccar.org/forums/topic/2023-ho-i-migrated-from-h2-to-postgres-in-linux/?utm_source=chatgpt.com) to create data_postgres.sql
12. **Setting up a postgreSQL database with Podman**  
   Used [Github - Podman for Windows](https://github.com/containers/podman/blob/main/docs/tutorials/podman-for-windows.md) and [Geeks for geeks - Set up a PostgreSQL database with podman](https://www.geeksforgeeks.org/devops/set-up-a-postgresql-database-with-podman/?utm_source=chatgpt.com) to set up my postgreSQL database with Podman.
13. **Add front-end server address to CORS**  
   Used [Kotlin CORS guide](https://www.stackhawk.com/blog/kotlin-cors-guide-what-it-is-and-how-to-enable-it/) to add front-end live server to cors.