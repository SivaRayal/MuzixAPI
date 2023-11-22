FROM openjdk:17.0.2-oracle
MAINTAINER Siva
WORKDIR /opt/app/home
COPY ./target/LastFMService-1.0.jar LastFMService.jar
EXPOSE 5020
ENV EUREKA_HOST=muzix-eureka-c1
ENV GATEWAY_HOST=muzix-gateway-c1
ENV GATEWAY_SWAGGER_HOST=docker.for.windows.localhost
ENTRYPOINT ["java","-jar","LastFMService.jar"]

# docker run -dt --name muzix-lasfm-service-c1 -p 5010:5010 --network muzix-backend muzix:muzix-lasfm-service-v1