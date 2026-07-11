FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x gradlew || true

CMD ["./gradlew", "clean", "test", "--no-daemon"]