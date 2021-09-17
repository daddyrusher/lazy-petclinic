FROM openjdk:14-alpine
LABEL Description="pet-clinic"
EXPOSE 8080
ARG JAR_FILE=pet-clinic-web/target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]