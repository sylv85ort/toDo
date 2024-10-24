# Use a base image with JDK 17 (or your version)
FROM openjdk:11

#install maven
RUN apt-get update && apt-get install -y maven

# Download and install Tomcat 7
RUN wget https://archive.apache.org/dist/tomcat/tomcat-7/v7.0.109/bin/apache-tomcat-7.0.109.tar.gz \
    && tar xvf apache-tomcat-7.0.109.tar.gz \
    && mv apache-tomcat-7.0.109 /usr/local/tomcat

	# Set the working directory inside the container
	WORKDIR /usr/local/tomcat/webapps/

# Build the application using Maven (if you use Maven)
RUN mvn package -DskipTests

COPY /app/target/toDo-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

# Expose port 8080 for your application (adjust as necessary)

EXPOSE 8085

# Start Tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"
