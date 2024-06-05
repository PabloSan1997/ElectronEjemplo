FROM openjdk:21-jdk-slim
ARG JAR_ROUTE=target/servicios-0.0.1-SNAPSHOT.jar
COPY ${JAR_ROUTE} app_service.jar
EXPOSE 3007
ENTRYPOINT ["java", "-jar", "app_service.jar"]

