# Stage 1: Build the project
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the image to run the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/digimon-api-0.0.1-SNAPSHOT.jar /app/digimon-api.jar

# Expose the application port
EXPOSE 5431

# Command to run the application
ENTRYPOINT ["java", "-jar", "digimon-api.jar"]
