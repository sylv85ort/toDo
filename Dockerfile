# Use JDK 11 as the base image
FROM openjdk:11

# Install Maven
RUN apt-get update && apt-get install -y maven wget

# Set the working directory for the app
WORKDIR /app

# Copy the project files into the container (assuming all source code is in the current directory)
ADD . .

# Build the application using Maven
RUN mvn clean package -DskipTests

# Download and install Tomcat 7
RUN wget https://archive.apache.org/dist/tomcat/tomcat-7/v7.0.109/bin/apache-tomcat-7.0.109.tar.gz \
    && tar xvf apache-tomcat-7.0.109.tar.gz \
    && mv apache-tomcat-7.0.109 /usr/local/tomcat

# Copy the generated WAR file to Tomcat's webapps directory
COPY target/toDo-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

# Expose port 8085 for the application
EXPOSE 8085

# Start Tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
