FROM openjdk:8-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY build/libs/test-project-0.0.1-SNAPSHOT.jar /app/test-project.jar
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/test-project.jar
