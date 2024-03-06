# Use an OpenJDK 17 runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled Spring Boot application JAR file into the container
COPY target/api_challenge_graphql-0.0.1-SNAPSHOT.jar /app

# Expose the port that your Spring Boot application uses
EXPOSE 8080

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "api_challenge_graphql-0.0.1-SNAPSHOT.jar"]
