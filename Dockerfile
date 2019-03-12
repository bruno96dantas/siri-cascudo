FROM openjdk:8-jdk-alpine

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY ./api/target/api-0.0.1-SNAPSHOT.jar /usr/src/app
EXPOSE 8080
CMD ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]