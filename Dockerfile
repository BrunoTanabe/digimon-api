# Etapa 1: Compilação do projeto
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar o arquivo pom.xml e resolver dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o código fonte do projeto e construir o JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem para execução da aplicação
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiar o JAR da etapa de build para o container
COPY --from=build /app/target/digimon-api-0.0.1-SNAPSHOT.jar /app/digimon-api.jar

# Expor a porta
EXPOSE 5431

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "digimon-api.jar"]
