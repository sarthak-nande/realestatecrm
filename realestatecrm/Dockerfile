# Stage 1: Build the jar
FROM maven:3.9.6-eclipse-temurin-17 as builder

WORKDIR /app
COPY realestatecrm/ .            # 👈 copy inner project folder contents
RUN mvn clean package -DskipTests

# Stage 2: Run the jar
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
