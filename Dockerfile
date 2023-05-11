FROM gradle:8.1-jdk17-alpine AS build

COPY --chown=gradle:gradlew . /home/gradle/src
WORKDIR /home/gradle/src
# Download and cache dependencies in Docker layers
RUN ./gradlew dependencies

RUN ./gradlew build --no-daemon

FROM openjdk:17.0.1-jdk-slim

EXPOSE 8080 5005

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/oauth*.jar /app/app.jar
ENV _JAVA_OPTIONS '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005'

ENTRYPOINT ["java", "-jar", "/app/app.jar"]