FROM openjdk:23-jdk-slim
WORKDIR /app
COPY target/digimon-api-0.0.1-SNAPSHOT.jar /app/digimon-api.jar
EXPOSE 5431
CMD ["java", "-jar", "digimon-api.jar"]

