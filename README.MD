## This API shows how to work with OAUTH and /LOGIN of Spring Security

## Technologies

API uses the following technologies:
- Java 17
- Spring Boot 3.0.5
- Spring Data JPA
- Lombok
- H2 SQL DB
- Gradle

### Running:
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

### If you want to build image and then run it in a container: 

```bash
docker build -t jackredd/jwt:1.0 .

docker run -d -p 8080:8080 -p 5005:5005 --name jwt-app jackredd/jwt:1.0

```