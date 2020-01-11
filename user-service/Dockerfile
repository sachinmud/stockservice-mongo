FROM openjdk:8u171-jre-alpine
COPY ./target/user-service-0.0.1-SNAPSHOT.jar /usr/src/stockservice/userservice/
WORKDIR /usr/src/stockservice/userservice
EXPOSE 8085
CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]
