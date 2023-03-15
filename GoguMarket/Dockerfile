FROM openjdk:11-jre 
ARG JAR_FILE=target/*.jar 
COPY ${JAR_FILE} app.jar 
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar", "/app.jar"]