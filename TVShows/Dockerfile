FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ./target/tvshows.jar app.jar
WORKDIR /
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080