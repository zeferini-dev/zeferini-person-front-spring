FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiar settings.xml customizado com mirror alternativo
COPY settings.xml /root/.m2/settings.xml

# Aumentar timeout do Maven
ENV MAVEN_OPTS="-Dorg.slf4j.simpleLogger.defaultLogLevel=WARN -Dmaven.wagon.http.connect.timeout=20000 -Dmaven.wagon.http.read.timeout=20000"

# Copiar pom.xml primeiro para aproveitar cache de layers
COPY pom.xml .

# Baixar dependências com mirror alternativo
RUN mvn dependency:go-offline -B

# Copiar código fonte
COPY src ./src

# Compilar
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS="-Xms256m -Xmx512m"

ENTRYPOINT ["java", "-jar", "app.jar"]
