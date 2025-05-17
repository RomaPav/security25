#FROM openjdk:17
#ARG JAR_FILE=target/*.jar
#COPY ./target/microservice.jar app.jar
#ENTRYPOINT ["java","-jar","app.jar"]
FROM openjdk:17
COPY ./target/security25-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]