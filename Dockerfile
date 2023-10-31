
FROM maven:3.8.5-eclipse-temurin-11-alpine AS builder
WORKDIR /app
COPY meeting-common .
COPY meeting-gui .
COPY meeting-server .
COPY ./pom.xml .
COPY setting.xml .
#RUN sed -i s@/archive.ubuntu.com/@/mirrors.aliyun.com/@g ~/.m2/settings.xml && \
RUN mvn -s setting.xml -f ./pom.xml clean install -DskipTests=true


ARG TAG=1.0
FROM openjdk:21-jdk
LABEL authors="王鹏"
ENV LANG=en_US.UTF-8 LANGUAGE=en_US:en LC_ALL=en_US.UTF-8
MAINTAINER pengpeng_on@163.com
ENV IMAGE_NAME=meeting-server
ENV SERVICE_NAME=meeting-server
ENV SERVICE_VERSION=1.0
ENV JAR_NAME=${SERVICE_NAME}-${SERVICE_VERSION}.jar
#COPY --from=builder /app/target/*.jar /app/*.jar

ENV SERVER_PORT=${PORT}

EXPOSE ${SERVER_PORT}

WORKDIR /app/service/${SERVICE_NAME}


#RUN echo "http://mirrors.aliyun.com/alpine/v3.8/main" > /etc/apk/repositories \
#    && echo "http://mirrors.aliyun.com/alpine/v3.8/community" >> /etc/apk/repositories \
#    && apk update upgrade \
#    && apk add --no-cache procps unzip curl bash tzdata \
#    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
#    && echo "Asia/Shanghai" > /etc/timezone \

#COPY target/${JAR_NAME} ./server-service.jar
COPY target/${JAR_NAME} ./app.jar

ENTRYPOINT ["java", "-jar","app.jar"]
