FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY build/libs/*-0.0.1-SNAPSHOT-plain.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
