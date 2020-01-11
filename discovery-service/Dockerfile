FROM openjdk:8u171-jre-alpine
COPY ./target/discovery-service-0.0.1-SNAPSHOT.jar /usr/src/stockservice/discoveryservice/
WORKDIR /usr/src/stockservice/discoveryservice
EXPOSE 8761
CMD ["java", "-jar", "discovery-service-0.0.1-SNAPSHOT.jar"]
