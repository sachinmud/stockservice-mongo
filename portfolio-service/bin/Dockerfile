FROM openjdk:8u171-jre-alpine
COPY ./target/portfolio-service-0.0.1-SNAPSHOT.jar /usr/src/stockservice/portfolioservice/
WORKDIR /usr/src/stockservice/portfolioservice
EXPOSE 8081
CMD ["java", "-jar", "portfolio-service-0.0.1-SNAPSHOT.jar"]
