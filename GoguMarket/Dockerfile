FROM openjdk:11-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar COPY Wallet_quiz /home/wallet_quiz 
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar", "/app.jar"]