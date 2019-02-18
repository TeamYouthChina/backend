FROM openjdk:11
WORKDIR /app
COPY target/youthchina-backend.jar /app/youthchina-backend.jar
COPY .circleci/spring.application.properties /app/application.properties
COPY .circleci/spring.config.properties /app/config.properties
ENTRYPOINT ["java","-jar","youthchina-backend.jar","--spring.config.location=/app/config.properties,/app/application.properties"]