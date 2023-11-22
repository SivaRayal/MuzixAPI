FROM --platform=linux/amd64/v8 openjdk:17.0.2-oracle
WORKDIR /opt/app
COPY ./target/ServiceRegistry-1.0.jar ServiceRegistry.jar
EXPOSE 8010
ENTRYPOINT ["java","-jar","ServiceRegistry.jar"]


# docker network create muzix-backend
# docker run -dt --name dockmysqldb --network muzix-backend -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=fsd mysql:8.0.32
# docker run -dt --name dockmongdb --network muzix-backend mongo:6.0.3
# docker run -dt --name muzix-eureka-c1 -p8010:8010 --network muzix-backend muzix:muzix-eureka-v1