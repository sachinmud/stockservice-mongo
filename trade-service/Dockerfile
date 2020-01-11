FROM openjdk:8u171-jre-alpine
COPY ./target/trade-service-0.0.1-SNAPSHOT.jar /usr/src/stockservice/tradeservice/
WORKDIR /usr/src/stockservice/tradeservice
EXPOSE 8082
CMD ["java", "-jar", "trade-service-0.0.1-SNAPSHOT.jar"]
