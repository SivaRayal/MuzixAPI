FROM openjdk:17.0.2-oracle
LABEL authors="Siva"
WORKDIR /opt/app
COPY ./target/Gateway-1.0.jar Gateway.jar
ENV EUREKA_HOST=muzix-eureka-c1
ENTRYPOINT ["java", "-jar","Gateway.jar"]

# docker run -dt --name muzix-gateway-c1 -p 8800:8800 --network muzix-backend muzix:muzix-gateway-v1