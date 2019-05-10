FROM java:8
WORKDIR /
ADD target/dictionary-0.0.1-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT [ "java", "-Dspring.profiles.active=mysql2", "-jar", "/dictionary-0.0.1-SNAPSHOT.jar"]