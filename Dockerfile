
FROM maven:3.6.0-jdk-11-slim AS build

WORKDIR /app

COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package


# # Versão final

FROM openjdk:11-jre-slim

COPY --from=build /app/target/*.jar /usr/local/lib/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/local/lib/projeto-aluno-0.0.1.jar"]




