FROM openjdk:8-jdk-alpine
COPY build/libs/*.jar app.jar
COPY mongo_keys_config.json mongo_keys_config.json
ENTRYPOINT ["java","-jar","/app.jar"]