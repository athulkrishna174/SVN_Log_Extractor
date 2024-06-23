# Use an official Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml file and the source code
COPY pom.xml .
COPY src ./src

# Package the application as a WAR file
RUN mvn clean package -DskipTests

# Use an official Tomcat image to run the application
FROM tomcat:10.0-jdk17-openjdk-slim

# Remove the default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the packaged WAR file to the webapps directory
COPY --from=build /app/target/logextractor.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]