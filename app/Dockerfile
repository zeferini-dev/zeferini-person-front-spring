# syntax=docker/dockerfile:1
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiar pom.xml primeiro para aproveitar cache de layers
COPY pom.xml .

# Baixar dependências (cacheado separadamente)
RUN --mount=type=cache,target=/root/.m2/repository \
    mvn dependency:go-offline -B

# Copiar código fonte
COPY src ./src

# Compilar (usando cache do .m2)
RUN --mount=type=cache,target=/root/.m2/repository \
    mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS="-Xms256m -Xmx512m"

ENTRYPOINT ["java", "-jar", "app.jar"]
