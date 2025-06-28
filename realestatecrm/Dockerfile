# Use official JDK image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled .jar file to the container
COPY target/*.jar app.jar

# Expose port 8080 (or whichever your app uses)
EXPOSE 8080

# Command to run your Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
