FROM --platform=linux/amd64 openjdk:8-jdk-slim

ARG JAR
ARG APP_DIRETORY_ARG="/app"

ENV APP_DIRECTORY=$APP_DIRETORY_ARG

USER root

COPY $JAR "$APP_DIRECTORY/whoami.jar"
RUN /bin/bash -c "chmod -R 770 ${APP_DIRECTORY}" && \
    useradd -c "cloud execution user" -g root cloudy

WORKDIR $APP_DIRECTORY

USER cloudy

ENTRYPOINT ["java", "-jar", "whoami.jar"]