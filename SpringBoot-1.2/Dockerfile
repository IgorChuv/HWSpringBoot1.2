FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
EXPOSE 8081
ADD target/dev.jar myapp.jar
ENTRYPOINT ["java","-jar","myapp.jar"]