# Spring Boot Application productApi

The only thing better than a Maven archetype is a repo you can fork with everything already setup. Skip the documentation and just fork-and-code. 

## Built With

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[Flyway](https://flywaydb.org/) - Version control for database
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[H2](https://www.h2database.com/html/main.html) - Open-Source Relational Database Management System in Memory
* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system 
* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.
* 	[JUnit 5](https://junit.org/junit5/) - Open-Source software friendly testing framework for Java
## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.arc.sbtest.SBtemplateApplication` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Intellij or Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

### Security

```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.security.oauth</groupId>
    <artifactId>spring-security-oauth2</artifactId>
    <version>2.3.6.RELEASE</version>
</dependency>
```

Spring Boot Starter Security main username is `wipro` and password is `123`

Automated dependency updates done via [Dependabot](https://dependabot.com/)

### URLs

|  URL |  Method |
|----------|--------------|
|`http://localhost:8080/products`                                | POST |
|`http://localhost:8080/products/{id}`                           | GET | 
|`http://localhost:8080/products/{id}`                           | DELETE |
|`http://localhost:8080/products/{id}/disable`                   | PUT | 
|`http://localhost:8080/products/enable`                         | GET | 
|`http://localhost:8080/products/disable`                        | GET | 

## Documentation

* [Swagger](http://localhost:8080/swagger-ui.html) - Documentation & Testing

## Files and Directories

The project (a.k.a. project directory) has a particular directory structure. A representative project is shown below:

```
.
├── Spring Elements
├── src
│   └── main
│       └── java
│           ├── com.wipro.productApi
│           ├── com.wipro.productApi.config
│           ├── com.wipro.productApi.context.product
│           ├── com.wipro.productApi.context.user
│           ├── com.wipro.productApi.exception
│           └── com.wipro.productApi.security
├── src
│   └── main
│       └── resources
│           └──application.yml
│           └──db
│              └──migration
│                 ├──V1__create_product_table.sql
│                 ├──V2__create_user_table.sql
│                 ├──V3__load_product_table.sql
│                 └──V4__load_user_table.sql
├── src
│   └── test
│       └── java
│           ├── com.wipro.productApi
│           ├── com.wipro.productApi.context.product
│           └──com.wipro.productApi.context.user
├── JRE System Library
├── Maven Dependencies
├── bin
├── logs
│   └── application.log
├── src
├── target
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── productApi.iml
└── README.md
```

## packages

- `context` — to hold our project context;
- `user` — to hold our users;
- `product` — to hold our product;
- `security` — security configuration;
- `config` — to configure swagger and filter;

- `resources/` - Contains all the static resources, templates and property files.
- `resources/application.yml` - It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file.

- `test/` - contains unit and integration tests

- `pom.xml` - contains all the project dependencies
