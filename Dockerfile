FROM openjdk:17-jdk-slim

WORKDIR /app

COPY simple-team-blog-0.0.6.20250904_beta.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS="-Dspring.profiles.active=prod"

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]