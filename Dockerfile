FROM gradle:alpine as builder
COPY src /app/src
COPY build.gradle /app
WORKDIR /app
USER root
RUN gradle bootJar

FROM openjdk:8-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=builder /app/build/libs/test-project-0.0.1-SNAPSHOT.jar /app/test-project.jar
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/test-project.jar
