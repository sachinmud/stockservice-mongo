FROM openjdk:8u171-jre-alpine
COPY ./target/stockservice-web-0.0.1-SNAPSHOT.jar /usr/src/stockservice/stockserviceui/
WORKDIR /usr/src/stockservice/stockserviceui
EXPOSE 8080
CMD ["java", "-jar", "stockservice-web-0.0.1-SNAPSHOT.jar"]
