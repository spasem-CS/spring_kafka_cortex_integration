FROM openjdk:8-jdk-alpine
COPY build/libs/*.jar app.jar
COPY events.log events.log
ENTRYPOINT ["java","-jar","/app.jar"]