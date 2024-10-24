# Use a base image with JDK 17 (or your version)
FROM openjdk:11-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . /app

# Build the application using Maven (if you use Maven)
RUN ./mvnw package -DskipTests

# Expose port 8080 for your application (adjust as necessary)
EXPOSE 8080

ENTRYPOINT ["java", "-jar","/todo.jar"]
