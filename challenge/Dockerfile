ARG BUILD_IMAGE=maven:3.8.3-openjdk-17
ARG RUNTIME_IMAGE=openjdk:17-alpine

#############################################################################################
###                Stage where Docker is pulling all maven dependencies                   ###
#############################################################################################
FROM ${BUILD_IMAGE} as dependencies

WORKDIR /app

COPY pom.xml ./

COPY lombok.config ./

#############################################################################################

#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM dependencies as build

COPY src ./src

RUN mkdir ./target

RUN mvn -B clean package

#############################################################################################

#############################################################################################
###                                       Main stage                                      ###
#############################################################################################

FROM ${RUNTIME_IMAGE} as main

ARG PROJECT_NAME

EXPOSE 8080

COPY --from=build /app/target/*.jar /app/service.jar

RUN  apk update && apk upgrade && apk add --no-cache ttf-dejavu

CMD ["java", "-jar", "/app/service.jar"]
#############################################################################################