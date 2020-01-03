FROM openjdk:8u171-jre-alpine
COPY ./target/config-service-0.0.1-SNAPSHOT.jar /usr/src/stockservice/configservice/
WORKDIR /usr/src/stockservice/configservice
EXPOSE 8888
CMD ["java", "-jar", "config-service-0.0.1-SNAPSHOT.jar"]
