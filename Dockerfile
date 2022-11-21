# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Add maintainer
MAINTAINER Slobodan Vrhovac <slobxns@gmail.com>

# Make port 8098 available to the world outside this container
EXPOSE 8098

# The application's jar file
ARG JAR_FILE=target/kb-cra-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} kb-editor.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/kb-editor.jar"]