FROM openjdk:17-jdk-slim

WORKDIR /app

COPY mkdir-0.0.2.20250901_beta.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS="-Dspring.profiles.active=prod"

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]