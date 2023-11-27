FROM openjdk:17.0.2-oracle
MAINTAINER Siva
WORKDIR /opt/app/home
COPY ./target/WishListService-1.0.jar WishlistService.jar
EXPOSE 5010
ENV MONGO_DB=whishlist_db
ENV MONGO_HOST=dockmongdb
ENV MONGO_ROOT_PASSWORD=root
ENV MONGO_PORT=27017
ENV EUREKA_HOST=muzix-eureka-c1
ENV GATEWAY_HOST=muzix-gateway-c1
ENV GATEWAY_SWAGGER_HOST=docker.for.windows.localhost
ENTRYPOINT ["java","-jar","WishlistService.jar"]

# docker run -dt --name muzix-wishlist-c1 -p 5010:5010 --network muzix-backend muzix:muzix-wishlist-v1