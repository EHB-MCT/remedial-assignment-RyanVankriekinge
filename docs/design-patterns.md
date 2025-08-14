# Design patterns
## Spring boot
This is a list of all design patterns used in this application for spring boot.
### Strategy via Dependency injection
Dependency Injection (DI) is a design pattern that allows the Spring framework to inject dependencies into objects at runtime, rather than having objects create their dependencies manually.

Used in: For development I use an H2 memory database while for production postgreSQL will be used. This is done using dependency injection at run time.

Sources: 
1. [DEV.to - Master Core Spring Boot Concepts: Inversion of Control, Dependency Injection, and Your First Spring Boot Application](https://dev.to/techeazy_consulting/master-core-spring-boot-concepts-inversion-of-control-dependency-injection-and-your-first-spring-boot-application-3pp7#:~:text=Inversion%20of%20Control%20(IoC)%20is,simplifying%20dependency%20management%20and%20cleanup.)
2. [Geeks4Geeks - Strategy Design Pattern](https://www.geeksforgeeks.org/system-design/strategy-pattern-set-1/)

See set-up in README

### Model - View - Controller
The MVC pattern seperates the application in three layers:
1. Model: Data rules (tables and properties)
2. View: Renders the data
3. Controller: Processes user requests and passes them to the view

Sources:
1. [Geeks for geeks - Spring MVC Framework](https://www.geeksforgeeks.org/springboot/spring-mvc-framework/)

### Service layer
Core business logic is isolated from external dependencies, which allows for easier testing, maintainability and reusability.

Sources:
1. [Medium - Understanding the service layer in software architecture](https://medium.com/@navroops38/understanding-the-service-layer-in-software-architecture-df9b676b3a16)

### Repository design pattern
Used repository design pattern to manage data operations. This allows the use of fake repositories. I can easily switch from H2 to PostgreSQL for production without editing my service.

Sources:
1. [Repository pattern - What is it and why do we use it?](https://medium.com/@hunterfreas/repository-pattern-what-is-it-and-why-do-we-use-it-6a6a8e781711)

### Data mapper (ORM)
Hibernate maps entities to database tables automatically.

Sources:
1. [StackOverflow - Which pattern does hibernate follow?](https://stackoverflow.com/questions/1308096/which-pattern-does-hibernate-follow)
2. [Medium - What is the Data Mapper Pattern?](https://hosseinnejati.medium.com/what-is-the-data-mapper-pattern-219ce6484e28)

