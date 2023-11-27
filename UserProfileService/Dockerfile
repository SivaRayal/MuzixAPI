FROM openjdk:17.0.2-oracle
MAINTAINER Siva
WORKDIR /opt/app/home
COPY ./target/UserProfileService-1.0.jar UserProfileService.jar
EXPOSE 5005
ENV MYSQL_DB=fsd
ENV MYSQL_HOST=dockmysqldb
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_PORT=3306
ENV EUREKA_HOST=muzix-eureka-c1
ENV GATEWAY_HOST=muzix-gateway-c1
ENV GATEWAY_SWAGGER_HOST=docker.for.windows.localhost
ENTRYPOINT ["java","-jar","UserProfileService.jar"]


# docker run -dt --name muzix-userprofile-c1 -p 5005:5005 --network muzix-backend muzix:muzix-userprofile-v1