FROM eclipse-temurin:17-jdk-focal

WORKDIR /app
COPY . .

RUN ./gradlew clean build

CMD ["./gradlew", "bootRun"]