## This API shows how to work with OAUTH and /LOGIN of Spring Security

## Technologies

API uses the following technologies:
- Java 17
- Spring Boot 3.0.5
- Spring Data JPA
- Lombok
- H2 SQL DB
- Gradle

### Running
Fill in properties of 
```properties
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${PLACEHOLDER}
            client-secret: ${PLACEHOLDER}
```            
Then run the application