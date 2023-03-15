<<<<<<< HEAD
FROM openjdk:11-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar COPY Wallet_quiz /home/wallet_quiz 
=======
FROM openjdk:11-jre 
ARG JAR_FILE=target/*.jar 
COPY ${JAR_FILE} app.jar 
>>>>>>> branch 'main' of https://github.com/UNIKIM98/GoguMarket.git
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar", "/app.jar"]