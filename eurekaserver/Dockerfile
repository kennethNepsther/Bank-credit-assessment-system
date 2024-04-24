FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY  --from=build /app/target/*.jar ./eurekaserver.jar
#COPY ./target/eurekaserver-0.0.1-SNAPSHOT.jar eurekaserver.jar
EXPOSE 8761
ENTRYPOINT java -jar eurekaserver.jar