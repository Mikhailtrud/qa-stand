FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY . .
RUN chmod +x gradlew && ./gradlew bootJar

EXPOSE 8090
ENTRYPOINT ["java", "-jar", "build/libs/audit-service-0.0.1-SNAPSHOT.jar"]
