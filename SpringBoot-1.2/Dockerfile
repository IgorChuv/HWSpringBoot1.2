FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
EXPOSE 8080
COPY /target/SpringBoot-1.2.-0.0.1-SNAPSHOT.jar dockertask.jar
ENTRYPOINT ["java","-jar","dockertask.jar"]