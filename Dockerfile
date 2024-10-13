# Etapa 1: Construir o projeto
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar o pom.xml e baixar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o código-fonte e construir o JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem para executar a aplicação
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiar o JAR da etapa de build
COPY --from=build /app/target/digimon-api-0.0.1-SNAPSHOT.jar /app/digimon-api.jar

# Expor a porta da aplicação
EXPOSE 5431

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "digimon-api.jar"]
