FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/medcardservice-0.0.1-SNAPSHOT.jar /app/medcardservice.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "medcardservice.jar"]