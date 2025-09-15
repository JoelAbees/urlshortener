FROM eclipse-temurin:22-jdk AS build

WORKDIR /app


COPY mvnw pom.xml ./
COPY .mvn .mvn

RUN ./mvnw dependency:go-offline

COPY src src

RUN ./mvnw clean package -DskipTests




FROM eclipse-temurin:22-jre

WORKDIR /app

COPY --from=build /app/target/urlshortener-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]