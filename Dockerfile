FROM openjdk:11
WORKDIR /app
ENV YOUTHCHINAACCESSKEYID=${YOUTHCHINAACCESSKEYID}
ENV YOUTHCHINAACCESSKEYIDKEYSECRET=${YOUTHCHINAACCESSKEYIDKEYSECRET}
ENV YOUTHCHINADBURL = ${YOUTHCHINADBURL}
COPY target/youthchina-backend.jar /app/youthchina-backend.jar
COPY src/main/resources/png2pdf.pdf /png2pdf.pdf
COPY .circleci/deploy.application.properties /app/application.properties
COPY .circleci/deploy.application.properties /app/config.properties
ENTRYPOINT ["java","-jar","youthchina-backend.jar", "--spring.config.location=classpath:/config.properties,classpath:/application.properties"]
