FROM openjdk:11.0.14-jdk-slim-buster as build
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw install

FROM tomcat:9.0 as production-stage
RUN apt-get update -y
COPY --from=build /app/target/hu22.war /usr/local/tomcat/webapps/
COPY ./tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
