FROM openjdk:11
WORKDIR /app
COPY target/youthchina-backend.jar /app/youthchina-backend.jar
ENTRYPOINT ["java","-jar","youthchina-backend.jar"]