# Use a base image with JDK 17 (or your version)
FROM openjdk:11

#install maven
RUN apt-get update && apt-get install -y maven

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . /app

# Build the application using Maven (if you use Maven)
RUN mvn package -DskipTests

# Expose port 8080 for your application (adjust as necessary)
EXPOSE 8080

ENTRYPOINT ["java", "-jar","/todo.jar"]
