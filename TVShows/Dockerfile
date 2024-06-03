FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY .env /app/.env
WORKDIR /
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080