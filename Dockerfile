# Simple Dockerfile example
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/store_apis-0.0.1-SNAPSHOT.jar app.jar
 # <--- Make sure this JAR name is correct
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]