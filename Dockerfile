FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/MediaSoft-1.0-SNAPSHOT.jar /app


CMD ["java", "-jar", "MediaSoft-1.0-SNAPSHOT.jar"]