FROM --platform=linux/arm64 openjdk:8-jdk-slim

ARG JAR
ARG APP_DIRETORY_ARG="/app"

ENV APP_DIRECTORY=$APP_DIRETORY_ARG

USER root

COPY src/main/resources/http-header-spec.json ${APP_DIRECTORY}/http-header-spec.json
COPY $JAR "$APP_DIRECTORY/whoami.jar"
RUN /bin/bash -c "chmod -R 777 ${APP_DIRECTORY}" && \
    useradd -c "cloud execution user" -g root cloudy

WORKDIR $APP_DIRECTORY

EXPOSE 80

USER cloudy

CMD ["java", "-jar", "whoami.jar"]
#ENTRYPOINT ["java", "-jar", "whoami.jar"]
