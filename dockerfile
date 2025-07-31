# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/retail-0.0.1-SNAPSHOT.jar /app/

COPY src/main/resources /app/resources

EXPOSE 8080

CMD ["java", "-jar", "retail-0.0.1-SNAPSHOT.jar"]