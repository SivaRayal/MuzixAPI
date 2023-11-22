FROM --platform=linux/amd64/v8 openjdk:17.0.2-oracle
MAINTAINER Siva
WORKDIR /opt/app/home
COPY ./target/AuthorizationService-1.0.jar AuthorizationService.jar
EXPOSE 5000
ENV MYSQL_DB=fsd
ENV MYSQL_HOST=dockmysqldb
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_PORT=3306
ENV EUREKA_HOST=muzix-eureka-c1
ENV GATEWAY_HOST=muzix-gateway-c1
ENV GATEWAY_SWAGGER_HOST=docker.for.windows.localhost
ENTRYPOINT ["java","-jar","AuthorizationService.jar"]


# docker run -dt --name muzix-auth-service-c1 -p 5000:5000 --network muzix-backend muzix:muzix-auth-service-v1

